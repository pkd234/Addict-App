package com.pankaj.addict.models;

import java.util.UUID;

public class AddictModel {
    private String uid;
    private String addictName;
    private Integer addictCounter;
    private Integer addictTarget;
    private String resetType;

    public AddictModel(String addictName, Integer addictTarget, String resetType, Integer addictCounter) {
        this.uid = UUID.randomUUID().toString();
        this.addictName = addictName;
        this.addictTarget = addictTarget;
        this.resetType = resetType;
        this.addictCounter = addictCounter;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddictName() {
        return addictName;
    }

    public void setAddictName(String addictName) {
        this.addictName = addictName;
    }

    public Integer getAddictCounter() {
        return addictCounter;
    }

    public void setAddictCounter(Integer addictCounter) {
        this.addictCounter = addictCounter;
    }

    public String getResetType() {
        return resetType;
    }

    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    public Integer getAddictTarget() {
        return addictTarget;
    }

    public void setAddictTarget(Integer addictTarget) {
        this.addictTarget = addictTarget;
    }
}
