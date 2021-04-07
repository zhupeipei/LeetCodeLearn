package com.test;

public class Key {
    private int key;

    public Key(int key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
