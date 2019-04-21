package com.example.mahmoudalzoghby.graduationproject.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSignIn {
    @SerializedName("message")
    @Expose
    String mesage;

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public String getMesage() {
        return mesage;
    }
}
