package com.andreicristianpetcu.incrementaltdd.before.model;

import java8.util.Optional;

public class Environment {

    private Server server;

    public Environment(){
    }

    public Environment(Server server) {
        this.server = server;
    }

    public Optional<Server> getServer() {
        return Optional.ofNullable(server);
    }
}