package com.andreicristianpetcu.incrementaltdd.before.model;

public class Environment {

    Server server;

    public Environment(Server server) {
        this.server = server;
    }

    public Server getServer() {
        return server;
    }
}