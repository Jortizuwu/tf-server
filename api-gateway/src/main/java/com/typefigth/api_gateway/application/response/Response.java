package com.typefigth.api_gateway.application.response;

public class Response {

    private int code;
    private String path;
    private boolean success;
    private Object data;
    private boolean isArray;
    private String ip;
    private String status;

    public Response(int code, String path, boolean success, Object data, boolean isArray, String ip, String status) {
        this.code = code;
        this.path = path;
        this.success = success;
        this.data = data;
        this.isArray = isArray;
        this.ip = ip;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public boolean getIsArray() {
        return isArray;
    }

    public void setIsArray(boolean array) {
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

    public static Response build(int code, String path, boolean success, Object data, boolean isArray, String ip,  String status) {
        return new Response(code, path, success, data, isArray, ip, status);
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
                ", status='" + status + '\'' +
                '}';
    }
}
