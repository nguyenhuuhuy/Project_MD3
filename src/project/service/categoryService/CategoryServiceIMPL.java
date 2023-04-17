package project.service.categoryService;

import project.config.Config;
import project.model.category.Category;


import java.util.List;

public class CategoryServiceIMPL implements ICategory{
    List<Category> categoryList = new Config<Category>().readFromFile(Config.PATH_CATEGORY);

    @Override
    public List<Category> findAll() {
        return categoryList;
    }

    @Override
    public void save(Category category) {
        if (findById(category.getId()) != null) {
            int index = categoryList.indexOf(findById(category.getId()));
            categoryList.set(index, category);
        } else {
            categoryList.add(category);
        }
        new Config<Category>().writeToFile(Config.PATH_CATEGORY,categoryList);
    }

    @Override
    public Category findById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getId() == id) {
                return categoryList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getId() == id) {
                categoryList.remove(i);
            }
        }
        new Config<Category>().writeToFile(Config.PATH_CATEGORY,categoryList);
    }
}
