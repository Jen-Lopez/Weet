package io.weet.demo.models;

import java.util.ArrayList;

public class  Menu {
    private ArrayList<String> menuGroups;
    private ArrayList<ArrayList<Dish>> dishes;

    public Menu() {
        menuGroups = new ArrayList<>();
        dishes = new ArrayList<>();
    }

    public ArrayList<String> getGroups () {
        return menuGroups;
    }

    public ArrayList<ArrayList<Dish>> getDishItems() {
        return dishes;
    }

    public void addMenuGroup(String group) {
        menuGroups.add(group);
    }

    public void addGroupDishes(ArrayList<Dish> dishList) {
        dishes.add(dishList);
    }
}
