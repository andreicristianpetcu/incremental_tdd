package com.andreicristianpetcu.incrementaltdd.before.common;

public interface Callback<T> {
    void done(T result);
}