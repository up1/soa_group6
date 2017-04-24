package com.shareservice;

/**
 * Created by super on 21/3/2560.
 */
public class ServiceStatus {

    private String response;
    private String message;

    public ServiceStatus() {
    }

    public ServiceStatus(String response, String message) {
        this.response = response;
        this.message = message;
    }

    public Boolean isResponse(){
        if (this.response.equals("success")){
            return true;
        }
        else{
            return false;
        }
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
