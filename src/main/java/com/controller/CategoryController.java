package com.controller;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DAO.CategoryDao;
import com.DAO.ProductDao; 
import com.entity.Category;

import excel.ExcelImporter;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("category")
public class CategoryController {
    
    @Autowired
    private CategoryDao dao;

    @Autowired
    private ProductDao productDao; 

    // Show all categories
    @GetMapping("/index") // Thay đổi thành @GetMapping
    public String showIndex(Model model) {
        Category item = new Category();
        model.addAttribute("item", item);
        List<Category> items = dao.findAll();
        model.addAttribute("items", items);
        return "category/index";
    }

    // Show edit form for a category
    @GetMapping("/edit/{id}") // Thay đổi thành @GetMapping
    public String showEditForm(Model model, @PathVariable("id") String id) {
        Category item = dao.findById(id).orElse(null);
        model.addAttribute("item", item);
        List<Category> items = dao.findAll();
        model.addAttribute("items", items);
        return "category/index";
    }

    // Create a new category
    @PostMapping("/create") // Thay đổi thành @PostMapping
    public String createCategory(@ModelAttribute Category item) {
        dao.save(item);
        return "redirect:/category/index"; 
    }

    // Update an existing category
    @PostMapping("/update") // Thay đổi thành @PostMapping
    public String updateCategory(@ModelAttribute Category item) {
        dao.save(item);
        return "redirect:/category/edit/" + item.getId(); 
    }

    // Delete a category
    @GetMapping("/delete/{id}") // Thay đổi thành @GetMapping
    public String deleteCategory(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        Category category = dao.findById(id).orElse(null);
        
        if (category != null && !category.getProducts().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete category with related products.");
            return "redirect:/category/index"; 
        }

        dao.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Category deleted successfully.");
        return "redirect:/category/index"; 
    }

    // Export categories to Excel
    @GetMapping("/exportExcel") // Giữ nguyên
    public void exportToExcel(HttpServletResponse response) throws IOException {
        // Thiết lập kiểu file và tên file tải về
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=categories.xlsx");

        // Tạo file Excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Categories");

        List<Category> categories = dao.findAll(); 
        int rowNum = 0;
        Row header = sheet.createRow(rowNum++);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");

        for (Category category : categories) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(category.getId());
            row.createCell(1).setCellValue(category.getName());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }


    // Import categories from Excel
    @PostMapping("/import")
    public ResponseEntity<String> importCategories(@RequestParam("file") MultipartFile file) {
        try {
            ExcelImporter<Category> importer = new ExcelImporter<>(Category.class);
            importer.importData(file); // Chỉ gọi importData
            return ResponseEntity.ok("Import successful");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing file");
        }
    }

}
