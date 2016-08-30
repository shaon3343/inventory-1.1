package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParseSpreadsheet {
	public boolean getDataFromSpreadsheet(File fileXls){


		ArrayList<ArrayList<String>> contents = new ArrayList<ArrayList<String>>();

		try{

			//FileInputStream fileInput;
			//fileInput = new FileInputStream(path);
			//BufferedInputStream bufferInput = new BufferedInputStream(fileInput);  
			//POIFSFileSystem poiFileSystem;
			//poiFileSystem = new POIFSFileSystem(bufferInput);
			//Biff8EncryptionKey.setCurrentUserPassword(password); 
			//HSSFWorkbook wb = new HSSFWorkbook(poiFileSystem, true);

			InputStream ExcelFileToRead = new FileInputStream(fileXls);
			XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);


			XSSFSheet sheet1 = wb.getSheetAt(0);

			DataFormatter formatter = new DataFormatter(Locale.getDefault());

			
			String flag ="no error";
			for(int i=1;i<=sheet1.getLastRowNum();i++){
				//System.out.println("-----loop-------");
				XSSFRow row = sheet1.getRow(i);
				if(isEmptyRow(row)){continue;}
				for(int j=0;j<row.getLastCellNum();j++){
					//System.out.println("-----loop2-------");
					XSSFCell cell=null;
					if(row!=null){
						cell = row.getCell(j);
					}
					else{break;}

					if(cell!=null){

						//System.out.println("cell vall.."+i+"   "+cell.getRichStringCellValue().getString().trim());
						//System.out.println("cell vall..");
						
						switch (cell.getCellType()) {
						case XSSFCell.CELL_TYPE_STRING:
							System.out.println(cell.getRichStringCellValue().getString());

							break;
						case XSSFCell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								System.out.println(formatter.formatCellValue(cell));
								//str.add(cell.getDateCellValue().toString());
								//System.out.println(formatter.formatCellValue(cell));
							} else {
								System.out.println(String.valueOf((long)cell.getNumericCellValue()));
								//System.out.println(cell.getNumericCellValue());
							}
							break;

						default:
							System.out.println("DEFAULT");
						}
						
					//	System.out.println(">>>>>>>>>>>>>>"+cell.getRichStringCellValue());
					//	cell.getRichStringCellValue().getString().trim();
						
					}
				}

			} 


			return true;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			
			return false;

		}

	}
	
	public static boolean isEmptyRow(XSSFRow row){

		boolean isEmptyRow = true;
		if (row == null) {
			return true;
		}
		if (row.getLastCellNum() <= 0) {
			return true;
		}

		for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
			XSSFCell cell = row.getCell(cellNum);
			if(cell != null && cell.getCellType() != XSSFCell.CELL_TYPE_BLANK && StringUtils.isNotBlank(cell.toString())){
				isEmptyRow = false;
			}  
		}
		return isEmptyRow;
	}
}
