package com.lhz.hongbaoapp.common;

/**
 * Created by LHZ on 2017/1/17.
 */
public class ResponseBean {
    private boolean result;
    private String message;

    public ResponseBean(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
