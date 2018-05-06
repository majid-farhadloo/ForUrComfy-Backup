package com.example.majid.forurcomfy.Data.model;

/**
 * Created by farha on 5/5/2018.
 */

public class OrderProcess {
    private String message;

    public OrderProcess(String message) {
        this.message = message;
    }

    public OrderProcess() {
    }

    @Override
    public String toString() {
        return "OrderProcess{" +
                "message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
