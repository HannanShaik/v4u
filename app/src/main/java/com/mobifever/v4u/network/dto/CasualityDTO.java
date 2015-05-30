package com.mobifever.v4u.network.dto;

import com.mobifever.v4u.V4UConstants;

import java.util.List;

/**
 * Created by hannan.sa on 30/05/15.
 */
public class CasualityDTO {

    private String personName;
    private V4UConstants.DisasterType disasterType;
    private String myLocation;
    private String phoneNumber;
    private Long date;
    private List<String> statusOfPerson;
    private String kindOfHelpNeeded;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public V4UConstants.DisasterType getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(V4UConstants.DisasterType disasterType) {
        this.disasterType = disasterType;
    }

    public String getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(String myLocation) {
        this.myLocation = myLocation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public List<String> getStatusOfPerson() {
        return statusOfPerson;
    }

    public void setStatusOfPerson(List<String> statusOfPerson) {
        this.statusOfPerson = statusOfPerson;
    }

    public String getKindOfHelpNeeded() {
        return kindOfHelpNeeded;
    }

    public void setKindOfHelpNeeded(String kindOfHelpNeeded) {
        this.kindOfHelpNeeded = kindOfHelpNeeded;
    }

    @Override
    public String toString() {
        return "CasualityDTO{" +
                "personName='" + personName + '\'' +
                ", disasterType='" + disasterType + '\'' +
                ", myLocation='" + myLocation + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", date=" + date +
                ", statusOfPerson=" + statusOfPerson +
                ", kindOfHelpNeeded='" + kindOfHelpNeeded + '\'' +
                '}';
    }
}
