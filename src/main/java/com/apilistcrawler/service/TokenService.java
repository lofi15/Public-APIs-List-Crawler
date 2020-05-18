package com.apilistcrawler.service;

import com.apilistcrawler.response.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
public class TokenService {

    @Value("${token.url}")
    private String tokenUrl;

    @Autowired
    private RestTemplate restTemplate;

    public static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    public AccessToken getAuthToken(){

        ResponseEntity responseEntity;

        try{
            responseEntity = restTemplate
                    .exchange(tokenUrl, HttpMethod.GET,null, AccessToken.class);

        }catch (HttpClientErrorException.TooManyRequests exception){

            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return new AccessToken();
            }
            return getAuthToken();

        }

        AccessToken accessToken = (AccessToken) responseEntity.getBody();

//        LocalDateTime.parse("",sdf);
        accessToken.setExpirationDateAndTime(LocalDateTime.now().plusMinutes(4));
        return accessToken;

    }

//    public LocalDateTime extractDate(HttpHeaders header){
//
//        StringBuilder dateString = new StringBuilder();
//        header.get("Date").stream().forEach( s -> {dateString.append(s).substring(5);});
//        System.out.println("dateString :"+dateString);
//        //dateString.append(header.get("Date").get(0));
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss z");
//
//        LocalDateTime date = null;
//        date = LocalDateTime.parse(dateString.toString(),dtf);
//
//        logger.info("date converted :"+ date.toString());
//
//        return date;
//
//
//    }



}
