package com.transport.utils;

public enum DroneModel {
    LightWeight("LightWeight"),
    MiddleWeight("MiddleWeight"),
    CruiseWeight("CruiseWeight"),
    HeavyWeight("HeavyWeight");

    private String model;

    private DroneModel(String model) {
        this.model = model;
    }
}
