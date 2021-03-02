package com.reports.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

@RestController
public class apiController {

    public URL setup() {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "3128");

        URL url = null;
        try {
            url = new URL("https://api.kanye.rest/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            URLConnection con = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Autowired
    private RestTemplate restTemplate;

    private static String url = "setup()";

    @GetMapping("/countries")
    public List<Object> getCountries(){
        Object[] countries = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(countries);
    }
}
