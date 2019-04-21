package com.example.mahmoudalzoghby.graduationproject.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HelloWorld {

    @SerializedName("message")
    @Expose
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HelloWorld(String message) {
        this.message = message;
    }
}
