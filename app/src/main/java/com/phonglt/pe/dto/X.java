package com.phonglt.pe.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class X  implements Serializable {
    @SerializedName("name")
    private String name;

    @SerializedName("date")
    private String date;

    @SerializedName("gender")
    private String gender;

    @SerializedName("address")
    private String address;

    @SerializedName("idNganh")
    private String idNganh;

    @SerializedName("id")
    private String id;

    private Y y;

    public Y getY() {
        return y;
    }

    public void setY(Y y) {
        this.y = y;
    }

    public X() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNganh() {
        return idNganh;
    }

    public void setIdNganh(String idNganh) {
        this.idNganh = idNganh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public X(String name, String date, String gender, String address, String idNganh, String id) {
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.address = address;
        this.idNganh = idNganh;
        this.id = id;
    }
}
