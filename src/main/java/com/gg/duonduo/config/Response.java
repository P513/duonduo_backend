package com.gg.duonduo.config;

public class Response<T> {
    private T data;
    private boolean success;
    private String message;

    public Response(T data, boolean success, String message) {
        this.data = data;
        this.success = true;
        this.message=message;
    }

    public boolean getSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
