package br.com.dbserver.model;

import java.time.DayOfWeek;

public class Vote {

    private int id;
    private Person person;
    private Restaurant restaurant;
    private DayOfWeek dayOfWeek;

    public Vote(int id,  Person person, Restaurant restaurant, DayOfWeek dayOfWeek) {
        this.id = id;
        this.person = person;
        this.restaurant = restaurant;
        this.dayOfWeek = dayOfWeek;
    }

    public int getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public static Vote createVote(int personId, int restaurantId, DayOfWeek dayOfWeek) {
        Person person = new Person(personId, "generic person");
        Restaurant restaurant = new Restaurant(restaurantId, "generic restaurant");

        return new Vote(1, person, restaurant, dayOfWeek);
    }

}
