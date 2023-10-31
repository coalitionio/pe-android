package com.phonglt.pe.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Y implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public Y() {
    }

    public Y(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
