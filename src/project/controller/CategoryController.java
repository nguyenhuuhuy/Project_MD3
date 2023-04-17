package project.controller;

import project.model.category.Category;
import project.service.categoryService.CategoryServiceIMPL;

import java.util.List;

public class CategoryController {
    CategoryServiceIMPL categoryServiceIMPL = new CategoryServiceIMPL();

    public List<Category> showListCategory() {
        return categoryServiceIMPL.findAll();
    }

    public void createCategory(Category category) {
        categoryServiceIMPL.save(category);
    }

    public void deleteCategory(int id) {
        categoryServiceIMPL.deleteById(id);
    }

    public Category detailFastFood(int id) {
        return categoryServiceIMPL.findById(id);
    }
    public void updateCategory(Category category){
        categoryServiceIMPL.save(category);
    }
}
