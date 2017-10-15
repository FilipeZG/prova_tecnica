package br.com.dbserver.service.test;

import br.com.dbserver.model.Vote;
import br.com.dbserver.service.VoteService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;

public class VoteServiceTest {

    VoteService voteService;

    @Before
    public void init() {
        voteService = new VoteService();
    }

    @Test
    public void saveVoteTest() {
        Vote vote = Vote.createVote(1, 1, DayOfWeek.FRIDAY);
        voteService.saveVote(vote);

        int actualSize = voteService.listAll(DayOfWeek.FRIDAY).size();

        Assert.assertEquals(1, actualSize);
    }

    @Test(expected = RuntimeException.class)
    public void errorSaveTwiceInADayTest() {
        Vote vote = Vote.createVote(1, 1, DayOfWeek.FRIDAY);
        voteService.saveVote(vote);

        Vote vote2 = Vote.createVote(1, 3, DayOfWeek.FRIDAY);
        voteService.saveVote(vote2);
    }

    @Test
    public void votedAlreadyTrueTest() {
        Vote vote = Vote.createVote(1, 1, DayOfWeek.FRIDAY);
        voteService.saveVote(vote);

        boolean votedAlready = voteService.votedAlready(vote);

        Assert.assertEquals(true, votedAlready);
    }

    @Test
    public void votedAlreadyFalseTest() {
        Vote vote = Vote.createVote(1, 1, DayOfWeek.FRIDAY);

        boolean votedAlready = voteService.votedAlready(vote);

        Assert.assertEquals(false, votedAlready);
    }

    @Test
    public void clearVotesTest() {
        Vote vote = Vote.createVote(1, 1, DayOfWeek.FRIDAY);
        voteService.saveVote(vote);

        int sizeBeforeClear = voteService.listAll(DayOfWeek.FRIDAY).size();

        Assert.assertEquals(1, sizeBeforeClear);

        voteService.clear();

        int sizeAfterClear = voteService.listAll(DayOfWeek.FRIDAY).size();

        Assert.assertEquals(0, sizeAfterClear);
    }

    @After
    public void end() {
        voteService.clear();
    }

}
