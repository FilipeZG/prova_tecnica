package br.com.dbserver.service;

import br.com.dbserver.dao.VoteDao;
import br.com.dbserver.model.Person;
import br.com.dbserver.model.Vote;

import java.time.DayOfWeek;
import java.util.List;

public class VoteService {

    private VoteDao voteDao = new VoteDao();

    public List<Vote> listAll(DayOfWeek dayOfWeek) {
        return voteDao.listAll(dayOfWeek);
    }

    public void saveVote(Vote vote) {
        validateVotedAlready(vote);
        voteDao.saveVote(vote);
    }

    private void validateVotedAlready(Vote vote) {
        boolean votedAlready = votedAlready(vote);

        if (votedAlready)
            throw new RuntimeException("You already voted today");
    }

    public boolean votedAlready(Vote vote) {
        DayOfWeek dayOfWeek = vote.getDayOfWeek();
        Person person = vote.getPerson();

        return voteDao.listAll(dayOfWeek).stream()
                      .filter(v -> v.getPerson().equals(person))
                      .count() > 0;
    }

    public void clear() {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            voteDao.clear(dayOfWeek);
        }
    }

    public void clear(DayOfWeek dayOfWeek) {
        voteDao.clear(dayOfWeek);
    }

}
