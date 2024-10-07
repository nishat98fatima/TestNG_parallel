package Utilitiy;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class rough {
    public static void main(String[] args) {
        String excelFilePath = "Test_case.xlsx"; // Path to your Excel file

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("TestData"); // Get the first sheet
            JsonArray jsonArray = new JsonArray();

            // Get headers from the first row
            Row headerRow = sheet.getRow(0);
            int columns = headerRow.getPhysicalNumberOfCells();

            // Iterate through each row, starting from the second row
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                JsonObject jsonObject = new JsonObject();

                for (int j = 0; j < columns; j++) {
                    Cell headerCell = headerRow.getCell(j);
                    String columnName = headerCell.getStringCellValue();

                    Cell cell = row.getCell(j);
                    if (cell == null || cell.getCellType() == CellType.BLANK) {
                        jsonObject.add(columnName, null); // Add null for blank cells
                    } else {
                        switch (cell.getCellType()) {
                            case STRING:
                                jsonObject.addProperty(columnName, cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    jsonObject.addProperty(columnName, cell.getDateCellValue().toString());
                                } else {
                                    jsonObject.addProperty(columnName, cell.getNumericCellValue());
                                }
                                break;
                            default:
                                jsonObject.add(columnName, null);
                        }
                    }
                }
                jsonArray.add(jsonObject);
                System.out.println(jsonObject);
            }

            // Convert to JSON string
            Gson gson = new Gson();
            String jsonString = gson.toJson(jsonArray);
            System.out.println("JSONString" +jsonString);

            // Write JSON to file
            try (FileWriter file = new FileWriter("output.json")) {
                file.write(jsonString);
                System.out.println("JSON file created successfully!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    	
    }
