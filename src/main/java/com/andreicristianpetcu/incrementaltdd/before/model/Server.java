package com.andreicristianpetcu.incrementaltdd.before.model;

import java8.util.Optional;

public class Server {

    private String name;

    public Server() {
    }

    public Server(String name) {
        this.name = name;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}