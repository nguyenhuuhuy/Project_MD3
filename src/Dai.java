import project.controller.FastFoodController;
import project.controller.OderController;
import project.model.food.FastFood;
import project.model.oderList.Oder;

import java.util.*;

public class Dai {
    static OderController oderController = new OderController();
    static List<Oder> oderList = oderController.showListOder();
    static FastFoodController fastFoodController = new FastFoodController();
     static List<FastFood> fastFoodList = fastFoodController.showListFood();

    public static void main(String[] args) {
        Map<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < oderList.size(); i++) {
            for (int j = 0; j <  oderList.get(i).getListOderUser().size(); j++) {
                Integer value = 0;
//                System.out.println(oderListList.get(i).getListOderUser().get(j));\
                Integer key = oderList.get(i).getListOderUser().get(j).getId();
                if (map.containsKey(key)){
                    value +=  (map.get(key) + oderList.get(i).getListOderUser().get(j).getQuantity());
                    map.put(key, value);
                }else {
                    value = oderList.get(i).getListOderUser().get(j).getQuantity();
                    map.put(key, value);
                }
                System.out.println("i = " + i + ", j = " + j + ", map = " + map);
            }
        }
        System.out.println(map);
        System.out.println(map.keySet());
        for (int i = 0; i < fastFoodList.size(); i++) {

        }
    }

    public void test(){


//        List<FastFood> fastFoodOderUser = updateOder.getListOderUser();
//        boolean check = false;
//        for (int j = 0; j < fastFoodOderUser.size(); j++) {
//            if (fastFoodData.getId() == fastFoodOderUser.get(i).getId()) {
//                fastFoodOderUser.get(i).setQuantity(fastFoodOderUser.get(i).getQuantity() + 1);
//                check = true;
//                break;
//            }
//        }
//        if (!check){
//            fastFoodOderUser.add(fastFoodData);
//        }
//        updateOder.setStatus(true);
//        updateOder.setListOderUser(fastFoodOderUser);
//        oderList.add(oderList.size()-1,updateOder);
//        user.setListOder(oderList);
//        System.out.println(fastFoodOderUser);
//        System.out.println(fastFoodOderUser.size());
//        System.out.println(updateOder.isStatus());
    }
}
