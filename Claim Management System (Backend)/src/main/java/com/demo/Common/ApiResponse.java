package com.demo.Common;

public class ApiResponse {
	private Object data;
    private Object error;
    private Integer status;
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public Object getError() {
        return error;
    }
    public void setError(Object error) {
        this.error = error;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
