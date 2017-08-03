package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import POC.Gourab.App;

public class ExcelUtility {

	public ArrayList<String> readExcelFile() {
		ArrayList<String> testStepList = new ArrayList<String>();
		Constants cons = new Constants();
		try {
			
			FileInputStream file = new FileInputStream(new File(cons.getTemplatePath()));
					
			// Create Workbook instance holding reference to .xls file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				String testStep = "";

				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					testStep = testStep + cell.getStringCellValue() + " --- ";
				}
				testStepList.add(testStep);
			}
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return testStepList;
	}
}
