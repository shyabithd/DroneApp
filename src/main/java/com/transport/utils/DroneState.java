package com.transport.utils;

public enum DroneState {
    IDLE("IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    RETURNING("RETURNING");

    private String state;

    private DroneState(String state) {
        this.state = state;
    }
}
