package com.andreicristianpetcu.incrementaltdd.before;

public interface Callback<T> {
    void done(T result);
}