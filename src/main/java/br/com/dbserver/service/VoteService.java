package br.com.dbserver.service;

import br.com.dbserver.dao.VoteDao;
import br.com.dbserver.model.Vote;

import java.time.DayOfWeek;
import java.util.List;

public class VoteService {

    private VoteDao voteDao = new VoteDao();

    public List<Vote> listAll(DayOfWeek dayOfWeek) {
        return voteDao.listAll(dayOfWeek);
    }

    public void saveVote(Vote vote) {
        boolean votedAlready = voteDao.votedAlready(vote);

        if (votedAlready)
            throw new RuntimeException("You already voted today");

        voteDao.saveVote(vote);
    }

}
