package com.enterprise.project.demo1springboot.shared.model.response;


public class StandardResponse {
    private String message;
    private Object Data;
    private String StatusCode;

    public StandardResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }
}
