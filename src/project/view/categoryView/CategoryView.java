package project.view.categoryView;

import project.config.ColorConfig;
import project.config.Config;
import project.controller.CategoryController;
import project.controller.FastFoodController;
import project.model.category.Category;
import project.model.food.FastFood;

import java.util.List;

public class CategoryView {
    FastFoodController fastFoodController = new FastFoodController();
    List<FastFood> fastFoodList = fastFoodController.showListFood();
    CategoryController categoryController = new CategoryController();
    List<Category> categoryList = categoryController.showListCategory();

    // bảng dùng chung ok
    public void tableCategoly() {
        System.out.println(" .------------------------------.");
        System.out.println(" |  ID  |    Loại Sản phẩm      |");
        System.out.println(" '------------------------------'");
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.printf(" |  %-4s|    %-17s  |\n", categoryList.get(i).getId(), categoryList.get(i).getCategoryName());
        }
        System.out.println(" '------------------------------'");
    }

    // hiển bảng danh sách ok
    public void showTableCategory() {
        System.out.println(" .-----------------------------.");
        System.out.println(" |  ID  |    Loại Sản phẩm     |");
        System.out.println(" '-----------------------------'");
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.printf(" |  %-4s|    %-17s |\n", categoryList.get(i).getId(), categoryList.get(i).getCategoryName());
        }
        System.out.println(" '-----------------------------'");

        Config.backCategoryMenu();
    }

    // thêm mới ok
    public void createCategory() {
        int categoryId;
        if (categoryList.isEmpty()) {
            categoryId = 1;
        } else {
            categoryId = Config.findMaxIdByCategory(categoryList) + 1;
        }
        String categoryName;
        boolean check;
        do {
            System.out.print("Nhập loại sản phẩm: ");
            categoryName = Config.getString();
            if (categoryName.trim().equals("")) {
                System.out.println(ColorConfig.RED + "Không được để trống." + ColorConfig.RESET);
                check = false;
            } else if (!categoryName.matches("^[a-zA-Z\\p{L}\\s‘’]+$")) {
                System.out.println("Không được nhập số!!");
                check = false;
            } else if (categoryName.length() < 4 || categoryName.length() > 30) {
                System.out.println("Không được dưới 3 ký tự!!!");
                check = false;
            } else {
                check = true;
            }
        } while (!check);
        Category category = new Category(categoryId, categoryName);
        categoryController.createCategory(category);
        System.out.println(ColorConfig.GREEN + "Thêm mới thành công !!!" + ColorConfig.RESET);
        Config.backCategoryMenu();
    }

    // sửa ok
    public void editCategory() {
        tableCategoly();
        while (true) {
            System.out.print("Nhập loại sản phẩm muốn sửa theo " + ColorConfig.RED + "id: " + ColorConfig.RESET);
            int targetId = Config.getInteger();
            if (categoryController.detailFastFood(targetId) == null) {
                System.out.println(ColorConfig.RED + "không có loại sản phẩm này!" + ColorConfig.RESET);
            } else {
                String newName;
                boolean check;
                do {
                    System.out.print("Nhập tên mới cho sản phẩm: ");
                    newName = Config.getString();
                    if (newName.trim().equals("")) {
                        System.out.println(ColorConfig.RED + "Không được để trống." + ColorConfig.RESET);
                        check = false;
                    } else if (!newName.matches("^[a-zA-Z\\p{L}\\s‘’]+$")) {
                        System.out.println(ColorConfig.RED + "Không được nhập số!!" + ColorConfig.RESET);
                        check = false;
                    } else if (newName.length() < 4 || newName.length() > 30) {
                        System.out.println(ColorConfig.RED + "Không được dưới 3 ký tự!!!" + ColorConfig.RESET);
                        check = false;
                    } else {
                        check = true;
                    }
                } while (!check);
                Category newCategory = new Category(targetId, newName);
                categoryController.createCategory(newCategory);
                Category categoryUpdateFastFood = categoryController.detailFastFood(targetId);
                for (int i = 0; i < fastFoodList.size(); i++) {
                    if (categoryUpdateFastFood.getId() == fastFoodList.get(i).getCategory().getId()) {
                        fastFoodList.get(i).setCategory(newCategory);
                        fastFoodController.updateFastFood(fastFoodList.get(i));
                    }
                }
                System.out.println(ColorConfig.GREEN + "Sửa thành công !!!" + ColorConfig.RESET);
                Config.backCategoryMenu();
            }
        }
    }

    // xóa ok
    public void deleteCategory() {
        tableCategoly();
        while (true) {
            System.out.print("Chọn loại sản phẩm muốn xóa theo " + ColorConfig.RED + "id " + ColorConfig.RESET + ": ");
            int targetId = Config.getInteger();
            if (categoryController.detailFastFood(targetId) == null) {
                System.out.println(ColorConfig.RED_BRIGHT + "Không tìm thấy vui lòng nhập lại!" + ColorConfig.RESET);
            } else {
                System.out.print(ColorConfig.GREEN + "                      Bạn có chắc muốn xóa không !!!!\n" + ColorConfig.RESET + ColorConfig.BLUE + "                          1.Yes" + ColorConfig.RESET + "          " + ColorConfig.RED + "2.No\n" + ColorConfig.RESET);
                while (true) {
                    try {
                        System.out.print("Mời Chọn: ");
                        int deleteCategory = Config.getInteger();
                        switch (deleteCategory) {
                            case 1:
                                categoryController.deleteCategory(targetId);
                                System.out.println(ColorConfig.GREEN + "Xóa thành công!" + ColorConfig.RESET);
                                Config.backCategoryMenu();
                            case 2:
                                Config.backCategoryMenu();
                        }
                    } catch (NumberFormatException err) {
                        System.out.println(ColorConfig.RED_BRIGHT + "Nhập sai định dạng, vui lòng nhập lại!" + ColorConfig.RESET);
                    }
                }
            }
        }
    }
}
