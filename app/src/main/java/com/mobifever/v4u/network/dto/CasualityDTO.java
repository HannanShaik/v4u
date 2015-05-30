package com.mobifever.v4u.network.dto;

import com.mobifever.v4u.V4UConstants;

import java.util.List;

/**
 * Created by hannan.sa on 30/05/15.
 */
public class CasualityDTO {


    private Integer casualityId;
    private Integer disasterId;
    private String personName;
    private String disasterType;
    private String myLocation;
    private String phoneNumber;
    private Long time;
    private List<String> statusOfPerson;
    private String kindOfHelpNeeded;

    public Integer getCasualityId() {
        return casualityId;
    }

    public void setCasualityId(Integer casualityId) {
        this.casualityId = casualityId;
    }

    public Integer getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(Integer disasterId) {
        this.disasterId = disasterId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
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
                "casualityId=" + casualityId +
                ", disasterId=" + disasterId +
                ", personName='" + personName + '\'' +
                ", disasterType='" + disasterType + '\'' +
                ", myLocation='" + myLocation + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", time=" + time +
                ", statusOfPerson=" + statusOfPerson +
                ", kindOfHelpNeeded='" + kindOfHelpNeeded + '\'' +
                '}';
    }
}
