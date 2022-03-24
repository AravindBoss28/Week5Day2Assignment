package week5Day1.Assignments;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class CreateTestData{
	static File exFile = new File("./TestData/LeadData.xlsx");
	public String[][] newTestData(int row, int column) throws IOException {
		String[] s = {"TEST","ARAVIND","BOSS"};
		String[][] data = new String[row-1][column];
		XSSFWorkbook excel = new XSSFWorkbook();
		XSSFSheet currentWB = excel.createSheet("LEAD");
		XSSFRow rowsCount = currentWB.createRow(0);
		rowsCount.createCell(0).setCellValue("COMPANYNAME");
		rowsCount.createCell(1).setCellValue("FIRSTNAME");
		rowsCount.createCell(2).setCellValue("LASTNAME");
		rowsCount.createCell(3).setCellValue("ID");
		for (int i = 0; i < row-1; i++) {
			int rand = (int) (Math.random() * 100);
			rowsCount = currentWB.createRow(i+1);
			for(int j = 0; j < column; j++) {
				rowsCount.createCell(j).setCellValue(s[j]+""+rand);
				data[i][j] = s[j] +""+ rand;
			}
		}
		if(exFile.exists()) {
			FileOutputStream fos = new FileOutputStream(exFile);
			excel.write(fos);
			excel.close();	
		} else {
			exFile = new File("./TestData/LeadData.xlsx");
			FileOutputStream fos = new FileOutputStream(exFile);
			excel.write(fos);
			excel.close();	
		}
		return data;
	}
}

public class DataProviderClass { 
	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	String xlFilePath;	    
	public DataProviderClass(String xlFilePath) throws Exception
	{
		this.xlFilePath = xlFilePath;
		fis = new FileInputStream(xlFilePath);
		workbook = new XSSFWorkbook(fis);
		fis.close();
	}

	public boolean setCellData(String sheetName, String colName, int rowNum, String value)
	{
		try
		{
			int col_Num = -1;
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
				{
					col_Num = i;
				}
			}
			sheet.autoSizeColumn(col_Num);
			row = sheet.getRow(rowNum - 1);
			if(row==null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(col_Num);
			if(cell == null)
				cell = row.createCell(col_Num);

			cell.setCellValue(value);
			fos = new FileOutputStream(xlFilePath);
			workbook.write(fos);
			fos.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return  false;
		}
		return true;
	}

	public String[] getColValue(String sheetName, String colName) {
		int col_Num = -1;
		sheet = workbook.getSheet(sheetName);
		int arraySize = sheet.getLastRowNum();
		String[] s = new String[arraySize];
		row = sheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) { 
			if (row.getCell(i).getStringCellValue().trim().equals(colName))
			{
				col_Num = i;
			}
		}
		
		for(int j = 1; j <= sheet.getLastRowNum(); j++) {
			s[j-1] =  sheet.getRow(j).getCell(col_Num).getStringCellValue().trim();
		}

		return s;
	}
}
