package br.com.dbserver.model;

public class Restaurant {


    private int id;
    private String name;

    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
