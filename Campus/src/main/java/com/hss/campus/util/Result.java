package com.hss.campus.util;

public class Result {
    private boolean result=false;
    private String url;

    public Result() {
    }

    public Result(boolean result, String url) {
        this.result = result;
        this.url = url;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                ", url='" + url + '\'' +
                '}';
    }
}
