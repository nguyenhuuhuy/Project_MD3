package project.controller;

import project.model.food.FastFood;
import project.service.flasFoodService.FastFoodServiceIMPL;

import java.util.List;

public class FastFoodController {
    FastFoodServiceIMPL fastFoodServiceIMPL = new FastFoodServiceIMPL();

    public List<FastFood> showListFood() {
        return fastFoodServiceIMPL.findAll();
    }

    public void createFastFood(FastFood fastFood) {
        fastFoodServiceIMPL.save(fastFood);
    }

    public void deleteFastFood(int id) {
        fastFoodServiceIMPL.deleteById(id);
    }

    public FastFood detailFastFood(int id) {
        return fastFoodServiceIMPL.findById(id);
    }

    public void updateFastFood(FastFood fastFood){
        fastFoodServiceIMPL.save(fastFood);
    }
}
