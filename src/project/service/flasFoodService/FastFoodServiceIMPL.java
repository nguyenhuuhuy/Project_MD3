package project.service.flasFoodService;

import project.config.Config;
import project.model.category.Category;
import project.model.food.FastFood;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FastFoodServiceIMPL implements IFastFoodService{
   public static List<FastFood> fastFoodList = new Config<FastFood>().readFromFile(Config.PATH_FOOD);
//   public static List<FastFood> fastFoodList = new ArrayList<>();


    @Override
    public List<FastFood> findAll() {
        return fastFoodList;
    }

    @Override
    public void save(FastFood fastFood) {
        if (findById(fastFood.getId()) != null) {
            int index = fastFoodList.indexOf(findById(fastFood.getId()));
            fastFoodList.set(index, fastFood);
        } else {
            fastFoodList.add(fastFood);
        }
        new Config<FastFood>().writeToFile(Config.PATH_FOOD, fastFoodList);
    }

    @Override
    public FastFood findById(int id) {
        for (int i = 0; i < fastFoodList.size(); i++) {
            if (fastFoodList.get(i).getId() == id) {
                return fastFoodList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < fastFoodList.size(); i++) {
            if (fastFoodList.get(i).getId() == id) {
                fastFoodList.remove(i);
            }
        }
        new Config<FastFood>().writeToFile(Config.PATH_FOOD, fastFoodList);
    }
}
