package vn.edu.hcmuaf.e_learningapp.common;

import java.io.Serializable;

public class ResponseDto <T> implements Serializable {
    private int status;
    private String message;
    private T data;

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
