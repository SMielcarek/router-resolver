package de.expertlead.recruitment.domain;

import java.util.ArrayList;
import java.util.List;

public class RouteElements {

    List<Element> elements = new ArrayList<>();
    List<String> params = new ArrayList<>();

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public void addElement(Element element) {
        elements.add(element);
        if (element.isParameter()) {
            params.add(element.getName());
        }
    }
}
