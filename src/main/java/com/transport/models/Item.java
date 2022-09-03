package com.transport.models;

public class Item {

    private String name;
    private String code;
    private Integer weight;
    private String image;

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getImage() {
        return image;
    }
}
