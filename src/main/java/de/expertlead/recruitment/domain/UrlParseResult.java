package de.expertlead.recruitment.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class UrlParseResult {

    private String scheme;
    private String host;
    private String path;
    private Map<String, String> parameters = new LinkedHashMap<>();

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(String name, String value) {
        parameters.put(name, value);
    }
}
