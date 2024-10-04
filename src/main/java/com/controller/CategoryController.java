package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DAO.CategoryDao;
import com.DAO.ProductDao; // Thêm DAO cho Product
import com.entity.Category;
import com.entity.Product;

@Controller
public class CategoryController {
    
    @Autowired
    private CategoryDao dao;
    
    @Autowired
    private ProductDao productDao; // Thêm ProductDao

    @RequestMapping("/category/index")
    public String ShowIndex(Model model) {
        Category item = new Category();
        model.addAttribute("item", item);
        List<Category> items = dao.findAll();
        model.addAttribute("items", items);
        return "category/index";
    }

    @RequestMapping("/category/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        Category item = dao.findById(id).orElse(null);
        model.addAttribute("item", item);
        List<Category> items = dao.findAll();
        model.addAttribute("items", items);
        return "category/index";
    }

    @RequestMapping(value = "/category/create", method = RequestMethod.POST)
    public String Create(Category item) {
        dao.save(item);
        return "redirect:/category/index";
    }

    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
    public String Update(Category item) {
        dao.save(item);
        return "redirect:/category/edit/" + item.getId();
    }

    @RequestMapping(value = "/category/delete/{id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        Category category = dao.findById(id).orElse(null);
        
        if (category != null && !category.getProducts().isEmpty()) {
            // Nếu có sản phẩm liên quan, không cho phép xóa
            redirectAttributes.addFlashAttribute("error", "Cannot delete category with related products.");
            return "redirect:/category/index"; // Redirect về trang index
        }

        // Nếu không có sản phẩm liên quan, tiến hành xóa category
        dao.deleteById(id);
        // Thêm thông điệp thành công
        redirectAttributes.addFlashAttribute("success", "Category deleted successfully.");
        return "redirect:/category/index"; // Redirect về trang index
    }


}
