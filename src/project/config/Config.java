package project.config;

import project.menu.CategoryMenuAdm;
import project.menu.FastFoodMenuAdm;
import project.menu.UserMenuAdm;
import project.model.category.Category;
import project.model.food.FastFood;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Config<T> {
    public static final String PATH_FOOD = "src/project/database/foodData.txt";
    public static final String PATH_CATEGORY = "src/project/database/categoryData.txt";
    public static final String PATH_LIST_ODER = "src/project/database/listOder.txt";
    public static final String PATH_USER = "src/project/database/user.txt";
    public static final String PATH_USER_LOGIN = "src/project/database/userLogin.txt";

    private static final String ERROR_ALERT = "=> Định dạng không hợp lệ, hoặc ngoài phạm vi! Vui lòng thử lại: ";
    private static final String EMPTY_ALERT = "=> Trường nhập vào không thể để trống! Vui lòng thử lại: ";

    /**
     * getString()  Return a String value from the user.
     */
    public static String getString() {
        while (true) {
            String result = getInput();
            if (result.equals("")) {
                System.err.print(EMPTY_ALERT);
                continue;
            }
            return result;
        }
    }

    /**
     * getChar()  Return a Character value from the user.
     */
    public static char getChar() {
        return getString().charAt(0);
    }

    /**
     * getBoolean()  Return a Boolean value from the user.
     */
    public static boolean getBoolean() {
        String result = getString();
        return result.equalsIgnoreCase("true");
    }

    /**
     * getByte()  Return a Byte value from the user.
     */
    public static byte getByte() {
        while (true) {
            try {
                return Byte.parseByte(getString());
            } catch (NumberFormatException errException) {
                System.err.print(ERROR_ALERT);
            }
        }
    }

    /**
     * getShort()  Return a Short value from the user.
     */
    public static short getShort() {
        while (true) {
            try {
                return Short.parseShort(getString());
            } catch (NumberFormatException errException) {
                System.err.print(ERROR_ALERT);
            }
        }
    }

    /**
     * getInteger()  Return a Integer value from the user.
     */
    public static int getInteger() {
        while (true) {
            try {
                return Integer.parseInt(getString());
            } catch (NumberFormatException errException) {
                System.err.print(ERROR_ALERT);
            }
        }
    }

    /**
     * getLong()  Return a Long value from the user.
     */
    public static long getLong() {
        while (true) {
            try {
                return Long.parseLong(getString());
            } catch (NumberFormatException errException) {
                System.err.print(ERROR_ALERT);
            }
        }
    }

    /**
     * getFloat()  Return a Float value from the user.
     */
    public static float getFloat() {
        while (true) {
            try {
                return Float.parseFloat(getString());
            } catch (NumberFormatException errException) {
                System.err.print(ERROR_ALERT);
            }
        }
    }

    /**
     * getDouble()  Return a Double value from the user.
     */
    public static double getDouble() {
        while (true) {
            try {
                return Double.parseDouble(getString());
            } catch (NumberFormatException errException) {
                System.err.print(ERROR_ALERT);
            }
        }
    }
    /*========================================Input Method End========================================*/

    /**
     * getInput()  Return any String value from the user.
     */
    private static String getInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * pressAnyKey()  Press any key to continue....
     */
    public static void pressAnyKey() {
        getInput();
    }

    /*========================================Other Method========================================*/
    // vòng chạy chọn số

    // findmaxId
    public static int findMaxIdByFastFood(List<FastFood> fastFoodList) {
        int maxId = fastFoodList.get(0).getId();
        for (int i = 0; i < fastFoodList.size(); i++) {
            if (maxId < fastFoodList.get(i).getId()) {
                maxId = fastFoodList.get(i).getId();
            }
        }
        return maxId;
    }

    public static int findMaxIdByCategory(List<Category> categoryList) {
        int maxId;
        maxId = categoryList.get(0).getId();
        for (int i = 0; i < categoryList.size(); i++) {
            if (maxId < categoryList.get(i).getId()) {
                maxId = categoryList.get(i).getId();
            }
        }
        return maxId;
    }

    // back menu
    public static void backCategoryMenu() {
        while (true) {
            System.out.print("Nhập " + ColorConfig.BLUE + "back " + ColorConfig.RESET + "để quay trở lại Menu: ");
            String backMenu = Config.getString();
            if (backMenu.trim().equalsIgnoreCase("back")) {
                new CategoryMenuAdm();
                break;
            }
        }
    }

    public static void backFastFoodMenu() {
        while (true) {
            System.out.print("Nhập " + ColorConfig.BLUE + "back " + ColorConfig.RESET + "để quay trở lại Menu: ");
            String backMenu = Config.getString();
            if (backMenu.trim().equalsIgnoreCase("back")) {
                new FastFoodMenuAdm();
            }
        }
    }

    public static void backUserMenu() {
        while (true) {
            System.out.print("Nhập " + ColorConfig.BLUE + "back " + ColorConfig.RESET + "để quay trở lại Menu: ");
            String backMenu = Config.getString();
            if (backMenu.trim().equalsIgnoreCase("back")) {
                new UserMenuAdm();
                break;
            }
        }
    }


    // phương thức đọc file
    public List<T> readFromFile(String pathFile) {
        List<T> tList = new ArrayList<>();
        File file = new File(pathFile);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            if (fileInputStream.available() != 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                tList = (List<T>) (objectInputStream.readObject());
                fileInputStream.close();
                objectInputStream.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found!!");
        } catch (IOException e) {
            System.err.println("IOE exception!!");
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found!!");
        }
        return tList;
    }

    // phương thức ghi file
    public void writeToFile(String pathFile, List<T> tList) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tList);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!!");
        } catch (IOException e) {
            System.out.println("IOE exception!!");
        }
    }

    // đăng xuất
    public synchronized void clearFile(String pathName) {
        File file = new File(pathName);
        try {
            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file, false);
            PrintWriter print = new PrintWriter(writer, false);
            print.flush();
            print.close();
            writer.close();
        } catch (Exception exception) {
            System.out.println("Exception have been caught");
        }
    }

    // sắp xếp top 10 món like
    public static void bubbleSortList(List<FastFood> fastFoodList) {
        for (int i = 0; i < fastFoodList.size() - 1; i++) {
            for (int j = fastFoodList.size() - 1; j > i; j--) {
                if (fastFoodList.get(j).getLikeUser().size() > fastFoodList.get(j - 1).getLikeUser().size()) {
                    FastFood temp = fastFoodList.get(j);
                    fastFoodList.set(j, fastFoodList.get(j - 1));
                    fastFoodList.set(j - 1, temp);
                }
            }
        }
    }

    public static boolean validateUserName(String userName) {
        final String regex = "[a-zA-Z0-9\\S]{1,40}";
        return Pattern.matches(regex, userName);
    }


}
