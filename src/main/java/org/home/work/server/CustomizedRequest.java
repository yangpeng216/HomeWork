package org.home.work.server;

import java.util.Map;

public class CustomizedRequest {

    private String method;
    private String url;
    private Map<String, Object> parameters;


    public String getMethod() {
        return method;
    }


    public String getUrl() {
        return url;
    }


    public Map<String, Object> getParameters() {
        return parameters;
    }

}
