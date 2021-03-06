package br.com.dbserver.dao;

import br.com.dbserver.model.Vote;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteDao {

    private static Map<DayOfWeek, List<Vote>> votes = new HashMap<>();

    static {
        votes.put(DayOfWeek.MONDAY, new ArrayList<>());
        votes.put(DayOfWeek.TUESDAY, new ArrayList<>());
        votes.put(DayOfWeek.THURSDAY, new ArrayList<>());
        votes.put(DayOfWeek.WEDNESDAY, new ArrayList<>());
        votes.put(DayOfWeek.FRIDAY, new ArrayList<>());
        votes.put(DayOfWeek.SATURDAY, new ArrayList<>());
        votes.put(DayOfWeek.SUNDAY, new ArrayList<>());
    }

    public List<Vote> listAll(DayOfWeek dayOfWeek) {
        return votes.get(dayOfWeek);
    }

    public void saveVote(Vote vote) {
        DayOfWeek dayOfWeek = vote.getDayOfWeek();
        votes.get(dayOfWeek).add(vote);
    }

    public void clear(DayOfWeek dayOfWeek) {
        votes.get(dayOfWeek).clear();
    }

}
