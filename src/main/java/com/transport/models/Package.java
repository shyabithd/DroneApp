package com.transport.models;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table
public class Package {

    @Id
    @Column
    @NotBlank(message = "Package name is required.")
    @Pattern(regexp = "[\\w*|_|-]*", message = "Invalid name specified")
    private String name;

    @Column
    @NotNull(message = "Package weight is required.")
    @Positive(message = "Package weight must be greater than 0")
    private int weight;

    @Column
    @NotBlank(message = "Package code is required.")
    @Pattern(regexp = "[\\[A-Z\\]*|_|-]*", message = "Invalid code specified")
    private String code;

    @Column
    @NotBlank(message = "Package img is required.")
    private String imgName;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "drone_serial_number", nullable = false)
    private Drone drone;

    @Column
    @NotBlank(message = "Package loaded timestamp is required.")
    private String timestamp;

    @Column
    @NotBlank(message = "Status is required")
    private String packageStatus;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgName() {
        return imgName;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return  " name: " + name +
                " weight: " + weight +
                " code: " + code +
                " image: " + imgName +
                " drone serial number: " + (drone != null ? drone.getSerialNumber() : "");
    }
}
