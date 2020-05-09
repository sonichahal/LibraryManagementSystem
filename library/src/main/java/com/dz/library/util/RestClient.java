package com.dz.library.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class RestClient {

    private final Logger LOG = LoggerFactory.getLogger(RestClient.class);

    public <T, C> C hitAPIByUri(URI uri, HttpMethod method, HttpEntity<T> entity, Class<C> c) {
        LOG.debug("Entering hitAPIByURI method of RestClient");
        try {
            LOG.debug("The resource End point :" + uri);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<C> response = restTemplate.exchange(uri, method, entity, c);
            if (response.hasBody()) {
                LOG.debug("Exiting hitApi method of RestClient");
                return response.getBody();
            } else {
                LOG.debug("Exiting hitApi method of RestClient");
                return (C) Boolean.TRUE;
            }
        } catch (RestClientException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}
