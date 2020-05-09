package com.dz.library.controller;

import com.dz.library.util.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
public class Test {

    @Value("${student.service.url}")
    private String studentService;

    @Autowired
    RestClient restClient;

    public Test() {
        this.restClient = new RestClient();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/library")
    public String library(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("userId", "");
        String url = studentService + "/helloWorld";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return response.getBody();
//        return "Hello Library World";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String getAPI() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder.fromUriString(studentService).path("/helloWorld").build().toUri();
        return restClient.hitAPIByUri(uri, HttpMethod.GET, requestEntity, String.class);
    }
}
