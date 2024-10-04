package excel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelExporter<T> {


    private final String[] columns; // Array of column names
    private final List<T> items;     // List of items to export

    public ExcelExporter(String[] columns, List<T> items) {
        this.columns = columns;
        this.items = items;
    }

    public void export(HttpServletResponse response, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook(); // Create a new workbook
        Sheet sheet = workbook.createSheet("Data"); // Create a new sheet

        // Create header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            headerRow.createCell(i).setCellValue(columns[i]);
        }

        // Create data rows
        int rowCount = 1;
        for (T item : items) {
            Row row = sheet.createRow(rowCount++);
            int colCount = 0;
            for (String column : columns) {
                String value = getPropertyValue(item, column); // Get property value
                row.createCell(colCount++).setCellValue(value);
            }
        }

        // Set response attributes for downloading the file
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        workbook.write(response.getOutputStream()); // Write workbook to response
        workbook.close(); // Close the workbook
    }

    // Uses reflection to get the property value of the item
    private String getPropertyValue(T item, String propertyName) {
        try {
            // Ensure that the property name is capitalized correctly
            return String.valueOf(item.getClass().getMethod("get" + capitalize(propertyName)).invoke(item));
        } catch (Exception e) {
            e.printStackTrace();
            return ""; // Return empty string in case of an error
        }
    }

    // Capitalizes the first letter of the property name
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str; // Handle empty or null strings
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
