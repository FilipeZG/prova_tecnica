package br.com.dbserver.service.test;

import br.com.dbserver.model.Restaurant;
import br.com.dbserver.model.Vote;
import br.com.dbserver.service.RestaurantService;
import br.com.dbserver.service.VoteService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.List;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;
    private VoteService voteService;

    @Before
    public void init() {
        restaurantService = new RestaurantService();
        voteService = new VoteService();
    }

    @Test
    public void listAllNotChosenTest() {
        Vote vote = Vote.createVote(1, 1, DayOfWeek.FRIDAY);
        voteService.saveVote(vote);

        Vote vote2 = Vote.createVote(2, 3, DayOfWeek.FRIDAY);
        voteService.saveVote(vote2);

        Vote vote3 = Vote.createVote(3, 3, DayOfWeek.FRIDAY);
        voteService.saveVote(vote3);

        List<Restaurant> restaurants = restaurantService.listAllNotChosen();
        Assert.assertEquals(6, restaurants.size());
    }

    @Test
    public void mostVotedTest() {
        Vote vote = Vote.createVote(1, 1, DayOfWeek.FRIDAY);
        voteService.saveVote(vote);

        Vote vote2 = Vote.createVote(2, 3, DayOfWeek.FRIDAY);
        voteService.saveVote(vote2);

        Vote vote3 = Vote.createVote(3, 3, DayOfWeek.FRIDAY);
        voteService.saveVote(vote3);

        long actualId = restaurantService.mostVoted(DayOfWeek.FRIDAY).getId();
        Assert.assertEquals(3, actualId);
    }

    @Test
    public void mostVotedIsNullTest() {
        Restaurant restaurant = restaurantService.mostVoted(DayOfWeek.FRIDAY);
        Assert.assertEquals(null, restaurant);
    }

    @After
    public void end() {
        voteService.clear();
    }

}
