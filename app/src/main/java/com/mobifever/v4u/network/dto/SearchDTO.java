package com.mobifever.v4u.network.dto;

import com.mobifever.v4u.V4UConstants;

import java.io.Serializable;

/**
 * Created by hannan.sa on 30/05/15.
 */
public class SearchDTO implements Serializable {

    private String personName;
    private String myLocation;
    private String disasterType;
    private int disasterId;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getLocation() {
        return myLocation;
    }

    public void setLocation(String location) {
        this.myLocation = location;
    }

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public int getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(int disasterId) {
        this.disasterId = disasterId;
    }

    @Override
    public String toString() {
        return "SearchDTO{" +
                "personName='" + personName + '\'' +
                ", location='" + myLocation + '\'' +
                ", disasterType=" + disasterType +
                ", disasterId=" + disasterId +
                '}';
    }
}
