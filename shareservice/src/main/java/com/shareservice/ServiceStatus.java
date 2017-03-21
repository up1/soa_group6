package com.shareservice;

/**
 * Created by super on 21/3/2560.
 */
public class ServiceStatus {

    private boolean response;
    private String message;

    public ServiceStatus() {
    }

    public ServiceStatus(boolean response, String message) {
        this.setResponse(response);
        this.setMessage(message);
    }


    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
