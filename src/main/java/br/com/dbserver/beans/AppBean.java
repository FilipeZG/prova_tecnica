package br.com.dbserver.beans;

import br.com.dbserver.dao.PersonDao;
import br.com.dbserver.model.Person;
import br.com.dbserver.model.Restaurant;
import br.com.dbserver.model.Vote;
import br.com.dbserver.service.RestaurantService;
import br.com.dbserver.service.VoteService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Named
@RequestScoped
public class AppBean {

    private Vote vote;
    private Restaurant mostVoted;
    private List<Restaurant> restaurants;
    private List<Person> persons;
    @Inject
    private RestaurantService restaurantService;
    @Inject
    private VoteService voteService;
    @Inject
    private PersonDao personDao;

    @PostConstruct
    private void load() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();

        vote = new Vote(1, null , null, dayOfWeek);
        mostVoted = restaurantService.mostVoted(dayOfWeek);
        persons = personDao.listAll();
        restaurants = restaurantService.listAllNotChosen();
    }

    public void save() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            voteService.saveVote(vote);
        } catch (RuntimeException e) {
            context.addMessage(null, new FacesMessage(e.getMessage()));
            return;
        }

        load();
        context.addMessage(null, new FacesMessage("Success"));
    }

    public Vote getVote() {
        return vote;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Restaurant getMostVoted() {
        return mostVoted;
    }

}
