package Utilitiy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Table.Cell;

public class GetDataFromExcel {

	public static String Readexcelfile(String Test_keyword, String value) throws Throwable {
		String result = "";
		System.out.println();
		try {
			File file = new File(System.getProperty("user.dir") + "/Test_case.xlsx");
			FileInputStream s = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(s);
			XSSFSheet sheet = wb.getSheet("TestScenario");
			int Rows = sheet.getPhysicalNumberOfRows();
			int Columns = sheet.getRow(0).getPhysicalNumberOfCells();
			HashMap<String, String> h1 = new HashMap<>();
			for (int i = 0; i < Rows; i++) {
				System.out.println(sheet.getRow(i).getCell(0).toString());
				if (sheet.getRow(i).getCell(0).toString().equals(Test_keyword)) {
					for (int j = 0; j < Columns; j++) {
						String var = "";
						if (sheet.getRow(i).getCell(j) != null) {
							var = sheet.getRow(i).getCell(j).toString();
						}
						h1.put(sheet.getRow(0).getCell(j).toString(), var);

					}

				}
			}
			System.out.println(h1.toString());
			result = h1.get(value);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(result);
		return result;

	}

	public static HashMap<String, String> CreateExcelmap(String Sheetname) throws Throwable {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			File file = new File(System.getProperty("user.dir") + "/Test_case.xlsx");
			FileInputStream s = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(s);
			XSSFSheet sheet = wb.getSheet(Sheetname);
			int Rows = sheet.getPhysicalNumberOfRows();
			// int Columns = sheet.getRow(0).getPhysicalNumberOfCells();
			// HashMap<String, String> h1 = new HashMap<>();
			for (int i = 1; i < Rows; i++) {

				map.put(sheet.getRow(i).getCell(0).toString(), sheet.getRow(i).getCell(1).toString());

			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
		// System.out.println(result);
		return map;

	}

	public static void main(String args[]) throws Throwable {

		// String var1 = Readexcelfile("TC_01", "name");
		// System.out.println(var1);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Actual_Response_Code","200");
		write_map_to_excel("TC_01", map);
	}

	public static HashMap<String, Object> read_excel_as_map(String scenario_keyword) throws Throwable {
		HashMap<String, Object> map_data = new HashMap<>();
		try {
			XSSFWorkbook wb = new XSSFWorkbook(
					new FileInputStream(new File(System.getProperty("user.dir") + "/Test_case.xlsx")));
			XSSFSheet ws = wb.getSheet("TestData");
			int rows = ws.getPhysicalNumberOfRows();
			int cols = ws.getRow(0).getPhysicalNumberOfCells();
			for (int i = 0; i < rows; i++) {
			if (ws.getRow(i).getCell(0).toString().equals(scenario_keyword)) {
				for (int j = 0; j < cols; j++) {
					map_data.put(ws.getRow(0).getCell(j).toString(), ws.getRow(i).getCell(j));
					  
				}
			}
			}
			wb.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return map_data;
	}

	public static void write_map_to_excel(String scenario_keyword, HashMap<String, String> write_map) throws Throwable {
		// HashMap<String, String> map_data = new HashMap<>();

		try {
			XSSFWorkbook wb = new XSSFWorkbook(
					new FileInputStream(new File(System.getProperty("user.dir") + "/Test_case.xlsx")));
			XSSFSheet ws = wb.getSheet("TestScenario");
			int rows = ws.getPhysicalNumberOfRows();
			int cols = ws.getRow(0).getPhysicalNumberOfCells();
			
			for (int i = 0; i < rows; i++) {
				System.out.println("loop1");

				if (ws.getRow(i).getCell(0).toString().equals(scenario_keyword)) {
					
					for (Map.Entry<String, String> indvdl_map_entry : write_map.entrySet()) {
						//System.out.println(indvdl_map_entry);
						System.out.println("loop2");
						String key_value = indvdl_map_entry.getKey();
						String write_value = indvdl_map_entry.getValue();
						for (int j = 0; j < cols; j++) {
							System.out.println("loop3");
							if (ws.getRow(0).getCell(j).toString().equals(key_value)) {
								XSSFCell cell=ws.getRow(i).createCell(j,CellType.BLANK);
								cell.setCellValue(write_value);
								System.out.println("Cell Value" +cell.getStringCellValue());
							}
						}
					}

				}
			}
			
			FileOutputStream fileOut = new FileOutputStream(
					new File(System.getProperty("user.dir") + "/Test_case.xlsx"));
			wb.write(fileOut);
			// closing the Stream
			fileOut.close();
			// closing the workbook

			wb.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void write_list_map_to_excel(ArrayList<HashMap<String, String>> write_list_map) throws Throwable {
		// HashMap<String, String> map_data = new HashMap<>();

		try {
			XSSFWorkbook wb = new XSSFWorkbook(
					new FileInputStream(new File(System.getProperty("user.dir") + "/Test_case.xlsx")));
			XSSFSheet ws = wb.getSheet("TestScenario");
			int rows = ws.getPhysicalNumberOfRows();
			int cols = ws.getRow(0).getPhysicalNumberOfCells();
			
			for(HashMap<String, String> indvdl_map : write_list_map) {
				
				for (int i = 0; i < rows; i++) {
					//System.out.println("loop1");

					if (ws.getRow(i).getCell(0).toString().equals(indvdl_map.get("scenario_keyword"))) {
						
						for (Map.Entry<String, String> indvdl_map_entry : indvdl_map.entrySet()) {
							//System.out.println(indvdl_map_entry);
							//System.out.println("loop2");
							String key_value = indvdl_map_entry.getKey();
							String write_value = indvdl_map_entry.getValue();
							for (int j = 0; j < cols; j++) {
								//System.out.println("loop3");
								if (ws.getRow(0).getCell(j).toString().equals(key_value)) {
									XSSFCell cell=ws.getRow(i).createCell(j,CellType.BLANK);
									cell.setCellValue(write_value);
									System.out.println("Cell Value" +cell.getStringCellValue());
								}
							}
						}

					}
				}
			}
			
			FileOutputStream fileOut = new FileOutputStream(
					new File(System.getProperty("user.dir") + "/Test_case.xlsx"));
			wb.write(fileOut);
			// closing the Stream
			fileOut.close();
			// closing the workbook

			wb.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static HashMap<String, String> read_excel_as_map(String scenario_keyword,String SheetName) throws Throwable {
		HashMap<String, String> map_data = new HashMap<>();
		try {
			XSSFWorkbook wb = new XSSFWorkbook(
					new FileInputStream(new File(System.getProperty("user.dir") + "/Test_case.xlsx")));
			XSSFSheet ws = wb.getSheet(SheetName);
			int rows = ws.getPhysicalNumberOfRows();
			int cols = ws.getRow(0).getPhysicalNumberOfCells();
			for (int i = 0; i < rows; i++) {
			if (ws.getRow(i).getCell(0).toString().equals(scenario_keyword)) {
				for (int j = 0; j < cols; j++) {
					map_data.put(ws.getRow(0).getCell(j).toString(), ws.getRow(i).getCell(j).toString());
					  
				}
			}
			}
			wb.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return map_data;
	}


}
