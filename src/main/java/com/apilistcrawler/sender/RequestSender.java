package com.apilistcrawler.sender;

import com.apilistcrawler.response.CategoriesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.net.URI;

@Component
public class RequestSender {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RequestSender.class);

    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity entity, Class<T> responseType){

        logger.info("request made : url {}",url," method {}",method);
        ResponseEntity responseEntity = null;
        try{
            responseEntity = restTemplate.exchange(url,method,entity,responseType);
        }catch (HttpClientErrorException.TooManyRequests errorException){

            try {
                Thread.sleep(60000);
                responseEntity = restTemplate.exchange(url,method,entity,responseType);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }

        return responseEntity;

    }



}
