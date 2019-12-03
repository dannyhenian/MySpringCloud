package com.danny.project.base;/**
 * Created by danny on 2018-01-09.
 */

/**
 * @author danny
 * @create 2018-01-09 18:23
 */

public class Info {
    class Status{
        public final static String AFTER="after";
        public final static String PRE="pre";
    }
    private Result result;
    private String status;
    private String url;


    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




}
