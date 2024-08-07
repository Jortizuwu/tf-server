package com.typefigth.match.application.response;

public class Response<T> {

    private int code;
    private String path;
    private boolean success;
    private T data;
    private boolean isArray;
    private String ip;
    private String message;
    private String status;

    public Response(int code, String path, boolean success, T data, boolean isArray, String ip, String message, String status) {
        this.code = code;
        this.path = path;
        this.success = success;
        this.data = data;
        this.isArray = isArray;
        this.ip = ip;
        this.message = message;
        this.status = status;
    }

    public Response() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static <T> Response<T> build(int code, String path, boolean success, T data, boolean isArray, String ip, String message, String status) {
        return new Response<>(code, path, success, data, isArray, ip, message, status);
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", path='" + path + '\'' +
                ", success=" + success +
                ", data=" + data +
                ", isArray=" + isArray +
                ", ip='" + ip + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
