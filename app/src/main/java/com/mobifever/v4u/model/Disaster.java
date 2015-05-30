package com.mobifever.v4u.model;

import com.mobifever.v4u.V4UConstants;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hannan.sa on 30/05/15.
 */
public class Disaster implements Serializable {

    private Integer disasterId;
    private V4UConstants.DisasterType disasterType;
    private String location;
    private Long time;
    private Integer numberOfCasualities;
    private List<String> helplineNumbers;
    private String disasterName;

    public Integer getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(Integer disasterId) {
        this.disasterId = disasterId;
    }

    public V4UConstants.DisasterType getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(V4UConstants.DisasterType disasterType) {
        this.disasterType = disasterType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getNumberOfCasualities() {
        return numberOfCasualities;
    }

    public void setNumberOfCasualities(Integer numberOfCasualities) {
        this.numberOfCasualities = numberOfCasualities;
    }

    public List<String> getHelplineNumbers() {
        return helplineNumbers;
    }

    public void setHelplineNumbers(List<String> helplineNumbers) {
        this.helplineNumbers = helplineNumbers;
    }

    public String getDisasterName() {
        return disasterName;
    }

    public void setDisasterName(String disasterName) {
        this.disasterName = disasterName;
    }

    @Override
    public String toString() {
        return "Disaster{" +
                "disasterId=" + disasterId +
                ", disasterType=" + disasterType +
                ", location='" + location + '\'' +
                ", time=" + time +
                ", numberOfCasualities=" + numberOfCasualities +
                ", helplineNumbers=" + helplineNumbers +
                ", disasterName='" + disasterName + '\'' +
                '}';
    }
}
