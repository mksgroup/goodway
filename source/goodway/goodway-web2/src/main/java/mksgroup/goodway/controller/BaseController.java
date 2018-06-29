/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mksgroup.java.common.BeanUtil;
import mksgroup.java.common.CommonUtil;
import mksgroup.java.poi.PoiUtil;

/**
 * This base controller provides common processing.
 * @author ThachLN
 */
abstract public class BaseController {
    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(BaseController.class);

    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

    final static int IDX_HEADERROW = 6;
    @Autowired
    private ServletContext servletContext;
 
    // http://localhost:8080/download1?fileName=abc.zip
    // Using ResponseEntity<InputStreamResource>
    @RequestMapping("/download1")
    public ResponseEntity<InputStreamResource> downloadData1(@RequestParam String fileName) throws IOException {
 
        MediaType mediaType = getMediaTypeForFileName(this.servletContext, fileName);
         
        String tempFolderPath = TMP_DIR + File.separator + "goodway" + UUID.randomUUID();
        String filePath = tempFolderPath + File.separator + UUID.randomUUID() + File.separator + getFilename();
        LOG.info("Store temporary file: " + filePath);
        
        FileOutputStream fo = new FileOutputStream(filePath);
        
        // Load data
        Workbook wb = PoiUtil.loadWorkbookByResource(getTemplate());
        wb.write(fo);
        fo.close();
        
        File file = new File(filePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
 
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + getFilename())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }
    
    @RequestMapping("/download2")
    public void downloadExcel(HttpServletResponse resonse) throws IOException {
 
        String fileName = getFilename();
        
        resonse.setContentType("application/vnd.ms-excel");
        
        // Content-Disposition
        resonse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
        
        // resonse.setContentLength((int) file.length());

        Workbook wb = PoiUtil.loadWorkbookByResource(getTemplate());
        Map<String, Integer> mapHeader = readHeader(wb);
        Sheet sheet = wb.getSheetAt(0);
        // Load data
        Iterable<?> listData = getDownloadData();
        
        // Start row of data
        int rowIdx = IDX_HEADERROW + 1;
        Integer headerIndex;
        for (Object obj : listData) {
            
            // Get all method getters
            try {
                // Get all getter
                Map<String, Method> mapMethod = BeanUtil.getReadMethodMap(obj);
                
                // Scan all header of template
                for (String key : mapHeader.keySet()) {
                    headerIndex = mapHeader.get(key);
                    
                 // Get getter of headerName
                    Method method = mapMethod.get(key);
                    if (method != null) {
                        try {
                            Object data = method.invoke(obj);
                            
                            // Write data to Excel.
                            PoiUtil.setContent(sheet, rowIdx, headerIndex, data);
                        } catch (Exception ex) {
                            LOG.error(String.format("Could not invoke method '%s'", method.getName()));
                        }
                    } else {
                        LOG.warn(String.format("Getter of property '%s' not found", key));
                    }
                    
                }

                rowIdx++;
                
                // Get method of header
            } catch (Exception ex) {
                LOG.error("Could not load ", ex);
            }
        }

        BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());
        wb.write(outStream);
        outStream.flush();

     }
    
    /**
     * Get all column name in the template.
     * @param wb  workbook of the template
     * @return all comment of the first row in the first sheet
     */
    private Map<String, Integer> readHeader(Workbook wb) {
        
        Map<String, Integer> headerMap = new HashMap<String, Integer>();
        Sheet sheet = wb.getSheetAt(0);
        
        Row headRow = sheet.getRow(IDX_HEADERROW);
        int lastCellNum = headRow.getLastCellNum();
        String headerValue;
        Comment headerComment;
        for (int i = headRow.getFirstCellNum(); i <= lastCellNum; i++) {
            headerComment = sheet.getCellComment(IDX_HEADERROW, i);

            headerValue = (headerComment != null && headerComment.getString() != null )? headerComment.getString().getString() : null;

            if (CommonUtil.isNNandNB(headerValue)) {
                headerMap.put(headerValue, i);
            }
        }

        return headerMap;
    }

    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        // application/pdf
        // application/xml
        // image/gif, ...
        String mineType = servletContext.getMimeType(fileName);
        try {
            MediaType mediaType = MediaType.parseMediaType(mineType);
            return mediaType;
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
    
    abstract String getFilename();
    abstract String getTemplate();
    abstract Iterable<?> getDownloadData();
}