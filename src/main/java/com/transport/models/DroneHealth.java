package com.transport.models;

import java.util.HashMap;

public class DroneHealth {

    private HashMap<String, Integer> map = new HashMap<>();

    public void setHealth(int health) {
        map.put("Battery capacity", health);
    }

    public Integer getHealth() {
       return map.getOrDefault("Battery capacity", 0);
    }
}
