package br.com.dbserver.service;

import br.com.dbserver.dao.RestaurantDao;
import br.com.dbserver.model.Restaurant;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RestaurantService {

    private VoteService voteService = new VoteService();
    private RestaurantDao restaurantDao = new RestaurantDao();

    public List<Restaurant> listAllNotChoosen() {
        List<Restaurant> restaurants = restaurantDao.listAll();
        List<Restaurant> choosenRestaurants = listAllChoosen();

        restaurants.removeAll(choosenRestaurants);

        return restaurants;
    }

    public List<Restaurant> listAllChoosen() {
        List<Restaurant> choosenRestaurants = new ArrayList<>();

        int todayCode =  LocalDate.now().getDayOfWeek().getValue();

        for (int dayCode = 1; dayCode < todayCode; dayCode++) {
            choosenRestaurants.add(mostVoted(dayCode));
        }

        return choosenRestaurants;
    }

    public  Restaurant mostVoted(int dayCode) {
        DayOfWeek dayOfWeek = DayOfWeek.of(dayCode);

        Map<Restaurant, Long> restaurantLongMap = voteService.listAll(dayOfWeek).stream()
                                                                                .collect(Collectors.groupingBy(v -> v.getRestaurant(), Collectors.counting()));

        return null;
    }

}
