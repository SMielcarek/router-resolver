package de.expertlead.recruitment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.expertlead.recruitment.domain.UrlParseResult;
import de.expertlead.recruitment.parsers.RouteTemplateParser;
import de.expertlead.recruitment.parsers.UrlParser;

public class Result {
    public static String ioWrapper(String route, String url) {
        String parseResult = "{}";
        RouteTemplateParser routeTemplateParser = new RouteTemplateParser(route);
        UrlParser urlParser = new UrlParser(routeTemplateParser);
        UrlParseResult urlParseResult = urlParser.parse(url);
        if (urlParseResult != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                parseResult = mapper.writeValueAsString(urlParseResult);
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        }
        return parseResult;
    }

}
