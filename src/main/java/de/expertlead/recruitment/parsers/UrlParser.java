package de.expertlead.recruitment.parsers;

import de.expertlead.recruitment.domain.UrlParseResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlParser {

    private final static String BASE_URL_PATTERN = "([a-z]+)://(.*?)";
    private RouteTemplateParser routeTemplateParser;
    private UrlParseResult urlParseResult;

    public UrlParser(RouteTemplateParser routeTemplateParser) {
        this.routeTemplateParser = routeTemplateParser;
    }

    public UrlParseResult parse(String url) {
        String urlPattern = "^" + BASE_URL_PATTERN + routeTemplateParser.prepareRoutePattern() + "$";
        Matcher urlMatcher = Pattern.compile(urlPattern).matcher(url);
        if (urlMatcher.find()) {
            urlParseResult = new UrlParseResult();
            urlParseResult.setScheme(urlMatcher.group(1));
            urlParseResult.setHost(urlMatcher.group(2));
            urlParseResult.setPath(urlMatcher.group(3));
            for (int i=4; i<=urlMatcher.groupCount(); i++) {
                if (urlMatcher.group(i) != null) {
                    urlParseResult.addParameter(routeTemplateParser.getParams().get(i-4), urlMatcher.group(i));
                }
            }
        }
        return urlParseResult;
    }

}
