package br.com.dbserver;

import org.wildfly.swarm.Swarm;

public class Main {

    public static void main(String[] args) throws Exception {
        Swarm swarm = new Swarm();

        swarm.start().deploy();
    }


}