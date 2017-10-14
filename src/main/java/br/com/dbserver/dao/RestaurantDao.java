package br.com.dbserver.dao;

import br.com.dbserver.model.Person;
import br.com.dbserver.model.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantDao {

    private static List<Restaurant> restaurants = new ArrayList<>();

    static {
        restaurants.add(new Restaurant(1, "Kikão"));
        restaurants.add(new Restaurant(2, "Alvi Azul"));
        restaurants.add(new Restaurant(3, "Emerttini"));
        restaurants.add(new Restaurant(4, "Restaurante do Chico"));
        restaurants.add(new Restaurant(5, "Pizza Tost"));
        restaurants.add(new Restaurant(6, "Tombado"));
        restaurants.add(new Restaurant(7, "Farol Lanches"));
    }

    public List<Restaurant> listAll() {
        return new ArrayList<>(restaurants);
    }

    public Restaurant findById(int id) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getId() == id)
                .collect(Collectors.toList()).get(0);
    }

}
