package com.seakie;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDictionary extends Dictionary {

	@Override
	protected void save(ArrayList<Word> results) throws IOException {
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("vocabulary");
		int rowCount = 0;
		
        for (Word word : results) {
            Row row = sheet.createRow(rowCount++);
             
            row.createCell(0);
            row.createCell(1);
            row.createCell(2);

            row.getCell(0).setCellValue(word.getWord());
            row.getCell(1).setCellValue(word.getCount());
            row.getCell(2).setCellValue(word.getMeaning());
        }
         
         
        try (FileOutputStream outputStream = new FileOutputStream("words.xlsx")) {
        	book.write(outputStream);
        }
	}

}
