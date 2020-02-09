package de.expertlead.recruitment.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Element {

    private String path;
    private String name;
    private boolean parameter;
    private boolean optional;

    public Element(String path) {
        this.path = path;
        this.optional = Pattern.compile("\\[/:?\\w*]").matcher(path).matches();
        this.parameter = Pattern.compile("\\[?/:\\w*]?").matcher(path).matches();
        this.name = getNameFromPath(path);
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public boolean isParameter() {
        return parameter;
    }

    public boolean isOptional() {
        return optional;
    }

    private String getNameFromPath(String path) {
        Matcher nameMatcher = Pattern.compile("\\w+").matcher(path);
        return nameMatcher.find() ? nameMatcher.group(0) : "";
    }
}
