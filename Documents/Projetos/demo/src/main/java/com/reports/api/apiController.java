package com.reports.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class apiController {



    @Autowired
    private RestTemplate restTemplate;

    private static String url = "https://restcountries.eu/rest/v2/all";
    private static String url1 = "http://formulario.smads.prefeitura.sp.gov.br/index.php/admin/remotecontrol";

    @GetMapping("/countries")
    public List<Object> getCountries(){
        Object[] countries = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(countries);
    }

    @GetMapping("/lime")
    public Object postLimeSurvey(){

        String[] admin = {"admin","admin_c2o2v2s"};

        JSONObject jo = new JSONObject();
        jo.put("method", "get_session_key");
        jo.put("id", 1);
        jo.put("params", admin);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(jo.toString(), headers);
        System.out.println(entity);

        Object response = restTemplate.postForObject(url1, entity, Object.class);
        System.out.println(response);
        return response;
    }

}
