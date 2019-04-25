package com.example.mahmoudalzoghby.graduationproject.User;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Workers {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("workers")
    @Expose
    private List<Worker> workers = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

}