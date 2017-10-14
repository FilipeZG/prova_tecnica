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
        List<Restaurant> chosenRestaurants = listAllChosen();

        restaurants.removeAll(chosenRestaurants);

        return restaurants;
    }

    private List<Restaurant> listAllChosen() {
        List<Restaurant> chosenRestaurants = new ArrayList<>();

        int todayCode =  LocalDate.now().getDayOfWeek().getValue();

        for (int dayCode = 1; dayCode < todayCode; dayCode++) {
            DayOfWeek dayOfWeek = DayOfWeek.of(dayCode);

            chosenRestaurants.add(mostVoted(dayOfWeek));
        }

        return chosenRestaurants;
    }

    public  Restaurant mostVoted(DayOfWeek dayOfWeek) {
        Restaurant mostVoted = null;
        Long maxVotes = 0L;

        Map<Restaurant, Long> restaurants = voteService.listAll(dayOfWeek).stream()
                                                                          .collect(Collectors.groupingBy(v -> v.getRestaurant(), Collectors.counting()));

        for (Restaurant restaurant : restaurants.keySet()) {
            Long votes = restaurants.get(restaurant);

            if (maxVotes < votes) {
                mostVoted = restaurant;
                maxVotes = votes;
            }
        }

        return mostVoted;
    }

}
