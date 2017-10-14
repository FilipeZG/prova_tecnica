package br.com.dbserver.service.test;

import br.com.dbserver.dao.PersonDao;
import br.com.dbserver.dao.RestaurantDao;
import br.com.dbserver.model.Person;
import br.com.dbserver.model.Restaurant;
import br.com.dbserver.model.Vote;
import br.com.dbserver.service.VoteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;

public class VoteServiceTest {

    VoteService voteService = new VoteService();
    PersonDao personDao = new PersonDao();
    RestaurantDao restaurantDao = new RestaurantDao();

    @Before
    public void init() {
        Vote vote = createVote(1, 1, DayOfWeek.WEDNESDAY);
        voteService.saveVote(vote);
    }

    @Test
    public void saveVoteTest() {
        Vote vote = createVote(1, 1, DayOfWeek.WEDNESDAY);
        voteService.saveVote(vote);

        int actualSize = voteService.listAll(DayOfWeek.FRIDAY).size();

        Assert.assertEquals(1, actualSize);
    }

    private Vote createVote(int personId, int restaurantId, DayOfWeek dayOfWeek) {
        Person person = personDao.findById(personId);
        Restaurant restaurant = restaurantDao.findById(restaurantId);

        return new Vote(1, person, restaurant, dayOfWeek);
    }

}
