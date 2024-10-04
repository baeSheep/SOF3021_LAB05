package excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ExcelImporter<T> {

    private final Class<T> type;

    // Cấu hình kết nối đến SQL Server
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=YourDatabaseName";
    private final String username = "yourUsername";
    private final String password = "yourPassword";

    public ExcelImporter(Class<T> type) {
        this.type = type;
    }

    public void importData(MultipartFile file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<T> items = new ArrayList<>();
            
            // Thực hiện kết nối đến cơ sở dữ liệu
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String sql = "INSERT INTO Categories (id, name) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                
                // Duyệt qua các hàng và bỏ qua tiêu đề
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) continue; // Bỏ qua hàng tiêu đề
                    T item = type.getDeclaredConstructor().newInstance(); // Tạo một thể hiện mới của T

                    // Giả định các thuộc tính được sắp xếp theo thứ tự trong file
                    for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                        String value = row.getCell(i).getStringCellValue();
                        setPropertyValue(item, i, value);
                        preparedStatement.setString(i + 1, value); // Gán giá trị cho preparedStatement
                    }
                    preparedStatement.addBatch(); // Thêm vào batch
                }
                
                // Thực thi batch insert
                preparedStatement.executeBatch();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPropertyValue(T item, int index, String value) {
        try {
            // Giả định tên thuộc tính được ánh xạ tới chỉ số
            String propertyName = getColumnName(index);
            item.getClass().getMethod("set" + capitalize(propertyName), String.class).invoke(item, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getColumnName(int index) {
        switch (index) {
            case 0: return "id"; // Giả định cột đầu tiên là 'id'
            case 1: return "name"; // Giả định cột thứ hai là 'name'
            // Thêm nhiều trường hợp cho các cột bổ sung
            default: return "property" + index;
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
