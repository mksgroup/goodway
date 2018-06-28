/**
 * Copyright 2018, MKS GROUP.
 */
package mksgroup.goodway.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

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

import mksgroup.java.poi.PoiUtil;

/**
 * This base controller provides common processing.
 * @author ThachLN
 */
abstract public class BaseController {
    /** For logging. */
    private final static Logger LOG = LoggerFactory.getLogger(BaseController.class);

    private static final String TMP_DIR = System.getProperty("java.io.tmpdir");

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
    public void downloadData2(HttpServletResponse resonse) throws IOException {
 
        String fileName = getFilename();
        MediaType mediaType = getMediaTypeForFileName(this.servletContext, fileName);

        
        resonse.setContentType(mediaType.getType());
        
        // Content-Disposition
        resonse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + getFilename());
        
        // resonse.setContentLength((int) file.length());

        // Load data
        Workbook wb = PoiUtil.loadWorkbookByResource(getTemplate());
        BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());
        wb.write(outStream);
        outStream.flush();

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
