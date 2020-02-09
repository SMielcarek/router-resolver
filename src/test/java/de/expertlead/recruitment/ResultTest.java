package de.expertlead.recruitment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.expertlead.recruitment.domain.UrlParseResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void ioWrapper_emptyRoute() throws JsonProcessingException {
        String route = "/";
        String url = "https://www.expertlead.com/";
        UrlParseResult urlParseResult = new UrlParseResult();
        urlParseResult.setScheme("https");
        urlParseResult.setHost("www.expertlead.com");
        urlParseResult.setPath("/");
        assertEquals(mapper.writeValueAsString(urlParseResult), Result.ioWrapper(route, url));
    }

    @Test
    public void ioWrapper_oneElementRoute() throws JsonProcessingException {
        String route = "/images";
        String url = "https://www.expertlead.com/images";
        UrlParseResult urlParseResult = new UrlParseResult();
        urlParseResult.setScheme("https");
        urlParseResult.setHost("www.expertlead.com");
        urlParseResult.setPath("/images");
        assertEquals(mapper.writeValueAsString(urlParseResult), Result.ioWrapper(route, url));
    }

    @Test
    public void ioWrapper_oneParameterRoute() throws JsonProcessingException {
        String route = "/:lang";
        String url = "https://www.expertlead.com/de";
        UrlParseResult urlParseResult = new UrlParseResult();
        urlParseResult.setScheme("https");
        urlParseResult.setHost("www.expertlead.com");
        urlParseResult.setPath("/de");
        urlParseResult.addParameter("lang","de");
        assertEquals(mapper.writeValueAsString(urlParseResult), Result.ioWrapper(route, url));
    }

    @Test
    public void ioWrapper_oneElementWithParameterRoute() throws JsonProcessingException {
        String route = "/article/:articleId";
        String url = "https://www.expertlead.com/article/123";
        UrlParseResult urlParseResult = new UrlParseResult();
        urlParseResult.setScheme("https");
        urlParseResult.setHost("www.expertlead.com");
        urlParseResult.setPath("/article/123");
        urlParseResult.addParameter("articleId","123");
        assertEquals(mapper.writeValueAsString(urlParseResult), Result.ioWrapper(route, url));
    }

    @Test
    public void ioWrapper_optionalParameterRoute_Present() throws JsonProcessingException {
        String route = "/article/:articleId/image[/:imageId]";
        String url = "https://www.expertlead.com/article/123/image/456";
        UrlParseResult urlParseResult = new UrlParseResult();
        urlParseResult.setScheme("https");
        urlParseResult.setHost("www.expertlead.com");
        urlParseResult.setPath("/article/123/image/456");
        urlParseResult.addParameter("articleId","123");
        urlParseResult.addParameter("imageId","456");
        assertEquals(mapper.writeValueAsString(urlParseResult), Result.ioWrapper(route, url));
    }

    @Test
    public void ioWrapper_optionalParameterRoute_NotPresent() throws JsonProcessingException {
        String route = "/article/:articleId/image[/:imageId]";
        String url = "https://www.expertlead.com/article/123/image";
        UrlParseResult urlParseResult = new UrlParseResult();
        urlParseResult.setScheme("https");
        urlParseResult.setHost("www.expertlead.com");
        urlParseResult.setPath("/article/123/image");
        urlParseResult.addParameter("articleId","123");
        assertEquals(mapper.writeValueAsString(urlParseResult), Result.ioWrapper(route, url));
    }

    @Test
    public void ioWrapper_missingParameterRoute() throws JsonProcessingException {
        String route = "/article/:articleId";
        String url = "https://www.expertlead.com/article";
        assertEquals("{}", Result.ioWrapper(route, url));
    }

    @Test
    public void ioWrapper_wrongPathRoute() throws JsonProcessingException {
        String route = "/article";
        String url = "https://www.expertlead.com/artxxx";
        assertEquals("{}", Result.ioWrapper(route, url));
    }
}