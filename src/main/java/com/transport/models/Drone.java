package com.transport.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Entity
@Table
public class Drone {

    @Id
    @Column
    @NotBlank(message = "Serial number is required.")
    @Size(min = 1, max = 100, message = "The length of Serial number must be between 1 and 100 characters.")
    private String serialNumber;

    @Column
    @NotBlank(message = "Drone model is required.")
    @Pattern(regexp = "^(LightWeight|MiddleWeight|CruiseWeight|HeavyWeight)$")
    private String droneModel;

    @Column
    @NotNull(message = "The weight is required.")
    @Positive(message = "The weight must be greater than 0")
    @Max(value = 500, message = "The weight must be less than 500")
    private int weight;

    @Column
    @NotNull(message = "The battery capacity is required.")
    @Min(value = 0, message = "The battery capacity must be greater than or equal 0")
    @Max(value = 100, message = "The battery capacity must be less than 100")
    private int batteryCapacity;

    @Column
    @NotBlank(message = "Drone state is required.")
    @Pattern(regexp = "^(IDLE|LOADING|LOADED|DELIVERING|DELIVERED|RETURNING)$", message = "Invalid Drone state")
    private String droneState;

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setDroneModel(String droneModel) {
        this.droneModel = droneModel;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public void setDroneState(String droneState) {
        this.droneState = droneState;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getDroneModel() {
        return droneModel;
    }

    public int getWeight() {
        return weight;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public String getDroneState() {
        return droneState;
    }

    @Override
    public String toString() {
        return  " serial number: " + serialNumber +
                " weight: " + weight +
                " model: " + droneModel +
                " capacity: " + batteryCapacity +
                " state: " + droneState;
    }
}
