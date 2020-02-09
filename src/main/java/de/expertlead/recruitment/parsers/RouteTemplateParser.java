package de.expertlead.recruitment.parsers;

import de.expertlead.recruitment.domain.Element;
import de.expertlead.recruitment.domain.RouteElements;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RouteTemplateParser {

    private final static String ROUTE_TEMPLATE_PATTERN = "(\\[?/:?\\w*]?)";
    private RouteElements routeElements = new RouteElements();

    public RouteTemplateParser(String route) {
        Matcher routeTemplateMatcher = Pattern.compile(ROUTE_TEMPLATE_PATTERN).matcher(route);
        while (routeTemplateMatcher.find()) {
            routeElements.addElement(new Element(routeTemplateMatcher.group(0)));
        }
    }

    public String prepareRoutePattern() {
        StringBuilder urlPatternBasedOnRoute = new StringBuilder();
        routeElements.getElements().forEach(element -> urlPatternBasedOnRoute.append(generatePaternElement(element)));
        return "(" + urlPatternBasedOnRoute.toString() + ")";
    }

    public List<String> getParams() {
        return routeElements.getParams();
    }

    private String generatePaternElement(Element element) {
        String wildcard = "";
        if (element.isOptional()) {
            wildcard = "*";
        }
        if (element.isParameter()) {
            return "/" + wildcard + "(\\w+)" + wildcard;
        }
        else {
            return element.getPath();
        }
    }

}
