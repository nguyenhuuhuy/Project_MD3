package project.menu;

import project.config.ColorConfig;
import project.config.Config;
import project.view.categoryView.CategoryView;

public class CategoryMenuAdm {
    public CategoryMenuAdm() {
            System.out.println("                                          .-------------------------------------------------------------." + "\n" +
                    "                                          |                   " + ColorConfig.BLUE + "CATEGORY      MANAGE " + ColorConfig.RESET + "                     |" + "\n" +
                    "                                          |-------------------------------------------------------------|" + "\n" +
                    "                                          |             1. Danh sách Category.                          |" + "\n" +
                    "                                          |             2. Thêm mới Category.                           |" + "\n" +
                    "                                          |             3. Sửa thông tin Category.                      |" + "\n" +
                    "                                          |             4. Xóa Category.                                |" + "\n" +
                    "                                          |             5. "+ColorConfig.RED+"Quay lại Menu Chính"+ColorConfig.RESET+".                         |" + "\n" +
                    "                                          '-------------------------------------------------------------'");
        int chooseManu;
        while (true) {
            do {
                System.out.println(ColorConfig.GREEN + ".------------------------------------------------------." + "\n" +
                        "|                                                      |" + ColorConfig.RESET);
                System.out.print(ColorConfig.BLUE_ITALIC + "     Mời chọn số : ➯ " + ColorConfig.RESET);
                chooseManu = Config.getInteger();
                System.out.println(ColorConfig.GREEN + "|                                                      |" + "\n" +
                        "'------------------------------------------------------'" + ColorConfig.RESET);
                if (chooseManu>5){
                    System.out.println("          Nhập quá " + ColorConfig.RED + "5 " + ColorConfig.RESET + "rồi bạn ơi !!!\uD83E\uDD10\uD83E\uDD10\uD83E\uDD10");
                }
            }while (chooseManu>5);
            switch (chooseManu) {
                case 1:
                    new CategoryView().showTableCategory();
                    break;
                case 2:
                    new CategoryView().createCategory();
                    break;
                case 3:
                    new CategoryView().editCategory();
                    break;
                case 4:
                    new CategoryView().deleteCategory();
                    break;
                case 5:
                    new ProfileMenu();
                    break;
                default:
                    System.out.println("Có từ " + ColorConfig.RED + "1 " + ColorConfig.RESET + "-----> " + ColorConfig.RED + "5 " + ColorConfig.RESET + "thôi bạn ơi, xin đó nhập lại đi !!!\uD83D\uDE14\uD83D\uDE14\uD83D\uDE14");
            }
        }

    }
}
