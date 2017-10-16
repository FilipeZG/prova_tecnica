package br.com.dbserver.service;

import br.com.dbserver.dao.RestaurantDao;
import br.com.dbserver.model.Restaurant;
import br.com.dbserver.model.Vote;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RestaurantService {

    private VoteService voteService = new VoteService();
    private RestaurantDao restaurantDao = new RestaurantDao();

    public List<Restaurant> listAllNotChosen(DayOfWeek today) {
        List<Restaurant> restaurants = restaurantDao.listAll();
        List<Restaurant> chosenRestaurants = listAllChosen(today);

        restaurants.removeAll(chosenRestaurants);

        return restaurants;
    }

    private List<Restaurant> listAllChosen(DayOfWeek today) {
        List<Restaurant> chosenRestaurants = new ArrayList<>();

        int todayCode =  today.getValue();

        IntStream.range(1, todayCode).forEach(dayCode -> {
            DayOfWeek dayOfWeek = DayOfWeek.of(dayCode);

            Restaurant mostVoted = mostVoted(dayOfWeek);

            if (mostVoted != null)
                chosenRestaurants.add(mostVoted);
        });

        return chosenRestaurants;
    }

    public  Restaurant mostVoted(DayOfWeek dayOfWeek) {
        List<Vote> votes = voteService.listAll(dayOfWeek);

        if (votes.isEmpty())
            return null;

        return votes.stream()
                    .collect(Collectors.groupingBy(v -> v.getRestaurant(), Collectors.counting()))
                    .entrySet().stream()
                               .max(Map.Entry.comparingByValue()).get().getKey();
    }

}
