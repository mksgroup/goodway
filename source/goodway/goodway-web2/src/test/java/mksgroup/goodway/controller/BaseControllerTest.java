package mksgroup.goodway.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import mksgroup.java.poi.PoiUtil;

class BaseControllerTest {

    @Test
    void testGetTemplate() {
        try {
            Workbook wb = PoiUtil.loadWorkbookByResource("/excel-templates/Template_Question.xlsx");
            Sheet sheet = wb.getSheetAt(0);
            
            Object headerValue = sheet.getRow(6).getCell(0).getStringCellValue();
            assertEquals("No", headerValue);
            
            headerValue = sheet.getRow(6).getCell(4).getStringCellValue();
            assertEquals("Question", headerValue);
            
            String headerComment = sheet.getCellComment(6, 4).getString().getString();
            assertEquals("question", headerComment);
            
        } catch (FileNotFoundException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

}
