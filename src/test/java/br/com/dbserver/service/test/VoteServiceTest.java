package br.com.dbserver.service.test;

import br.com.dbserver.dao.PersonDao;
import br.com.dbserver.dao.RestaurantDao;
import br.com.dbserver.model.Person;
import br.com.dbserver.model.Restaurant;
import br.com.dbserver.model.Vote;
import br.com.dbserver.service.VoteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.DayOfWeek;

public class VoteServiceTest {

    VoteService voteService = new VoteService();

    @Test
    public void saveVoteTest() {
        Vote vote = Vote.createVote(1, 1, DayOfWeek.FRIDAY);
        voteService.saveVote(vote);

        Vote vote2 = Vote.createVote(2, 1, DayOfWeek.FRIDAY);
        voteService.saveVote(vote2);

        Vote vote3 = Vote.createVote(3, 3, DayOfWeek.FRIDAY);
        voteService.saveVote(vote3);

        int actualSize = voteService.listAll(DayOfWeek.FRIDAY).size();

        Assert.assertEquals(3, actualSize);
    }

    @Test(expected = RuntimeException.class)
    public void saveVoteErrorTest() {
        Vote vote = Vote.createVote(1, 1, DayOfWeek.FRIDAY);
        voteService.saveVote(vote);

        Vote vote2 = Vote.createVote(1, 3, DayOfWeek.FRIDAY);
        voteService.saveVote(vote2);
    }

}
