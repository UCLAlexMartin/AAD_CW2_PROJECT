package exploreExcel;

import java.io.FileOutputStream; 
import java.io.IOException; 
import org.apache.poi.hssf.usermodel.*; 
import org.apache.poi.hssf.util.HSSFColor;

import ConnectionManager.*;
import java.sql.*;

public class ExportExcel {
	// temporarily use String[][] as input parameter
	public static void export(String[][] data, String path, String filename) throws IOException{
		
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("new sheet");
		
		for(int i=0;i<data.length;i++) {
			
			HSSFRow row = sheet.createRow(i); 
			
			for(int j=0;j<data[1].length;j++){
				row.createCell(j).setCellValue(data[i][j]); 
				
			}
			
		}
		
		// output path can be a parameter
		FileOutputStream fileOut = new FileOutputStream(path + "/" + filename); 
		wb.write(fileOut); 
		fileOut.close();
	}
}
