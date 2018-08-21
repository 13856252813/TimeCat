package com.cary.activity.timecat.model;

import java.io.Serializable;

public class BasicMealInfo implements Serializable {

    private String bridegroom, brideg, marryTime, shootTime, storeReceptionStr;

    public BasicMealInfo(String bridegroom, String brideg, String marryTime, String shootTime, String storeReceptionStr) {
        this.bridegroom = bridegroom;
        this.brideg = brideg;
        this.marryTime = marryTime;
        this.shootTime = shootTime;
        this.storeReceptionStr = storeReceptionStr;
    }

    public String getBridegroom() {
        return bridegroom;
    }

    public void setBridegroom(String bridegroom) {
        this.bridegroom = bridegroom;
    }

    public String getBrideg() {
        return brideg;
    }

    public void setBrideg(String brideg) {
        this.brideg = brideg;
    }

    public String getMarryTime() {
        return marryTime;
    }

    public void setMarryTime(String marryTime) {
        this.marryTime = marryTime;
    }

    public String getShootTime() {
        return shootTime;
    }

    public void setShootTime(String shootTime) {
        this.shootTime = shootTime;
    }

    public String getStoreReceptionStr() {
        return storeReceptionStr;
    }

    public void setStoreReceptionStr(String storeReceptionStr) {
        this.storeReceptionStr = storeReceptionStr;
    }

    @Override
    public String toString() {
        return "BasicMealInfo{" +
                "bridegroom='" + bridegroom + '\'' +
                ", brideg='" + brideg + '\'' +
                ", marryTime='" + marryTime + '\'' +
                ", shootTime='" + shootTime + '\'' +
                ", storeReceptionStr='" + storeReceptionStr + '\'' +
                '}';
    }
}
