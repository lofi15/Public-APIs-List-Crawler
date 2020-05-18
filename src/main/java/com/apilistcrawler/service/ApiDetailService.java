package com.apilistcrawler.service;

import com.apilistcrawler.entity.ApiDetailEntity;
import com.apilistcrawler.repository.ApiDetailsRepository;
import com.apilistcrawler.response.AccessToken;
import com.apilistcrawler.response.ApiDetailsResponse;
import com.apilistcrawler.response.CategoriesResponse;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class ApiDetailService {

    @Autowired
    private ApiDetailsRepository apiDetailsRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenService tokenService;

    @Value("${category.details.url}")
    String categoryDetailsUrl;

//    private String encodeValue(String value) {
//        try {
//            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        return value;
//    }
    public ApiDetailsResponse getAllApiDetails(String category){

        ApiDetailsResponse responseOnEachCall = null;
        ApiDetailsResponse apiDetails = new ApiDetailsResponse();

        int page=1;

        ResponseEntity responseEntity=null;

        AccessToken accessToken = tokenService.getAuthToken();


        //AccessToken accessToken =
        do{
            UriComponentsBuilder uriBuilder =  UriComponentsBuilder.fromHttpUrl(categoryDetailsUrl);
            uriBuilder.queryParam("category",category);
            uriBuilder.queryParam("page",page);
//            System.out.println(currUrl);

//            System.out.println("url :"+builder.build().encode().toString());
            try{
                HttpHeaders headers =new HttpHeaders();
                if(!accessToken.isValid()){
                    System.out.println("token became invlaid, fetching new one :");
                    accessToken = tokenService.getAuthToken();
                }
                headers.add("Authorization", String.valueOf("Bearer ")+accessToken.getToken());
                HttpEntity<String> reqEntity = new HttpEntity<String>("parameters", headers);

                //System.out.println(uriBuilder.build().encode().toUri());

                responseEntity = restTemplate.
                        exchange(uriBuilder.build().encode().toUri(),HttpMethod.GET,reqEntity,ApiDetailsResponse.class);

                //System.out.println("api call made, response :"+responseEntity.getBody().toString());


            }catch (HttpClientErrorException.TooManyRequests exception){
                //System.out.println("sleeping");
                try {
                    Thread.sleep(60000);
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
//            System.out.println("currPage :"+page);
//            System.out.println("response received :"+responseOnEachCall);
            responseOnEachCall = (ApiDetailsResponse) responseEntity.getBody();
            apiDetails.getCategories().addAll(responseOnEachCall.getCategories());
            apiDetails.setCount(responseOnEachCall.getCount());
            page++;

        }while(apiDetails.getCategories().size() < apiDetails.getCount());

//        System.out.println("final response :");
//        System.out.println(apiDetails);

        return apiDetails;

    }

    public void saveApiDetailsList(List<ApiDetailEntity> apiDetailEntityList){
            apiDetailsRepository.saveAll(apiDetailEntityList);
    }




}
