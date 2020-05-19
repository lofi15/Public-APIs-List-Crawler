package com.apilistcrawler.service;

import com.apilistcrawler.entity.ApiDetailEntity;
import com.apilistcrawler.repository.ApiDetailsRepository;
import com.apilistcrawler.response.AccessToken;
import com.apilistcrawler.response.ApiDetailsResponse;
import com.apilistcrawler.response.CategoriesResponse;
import com.apilistcrawler.sender.RequestSender;
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
    private RequestSender requestSender;

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

            HttpHeaders headers =new HttpHeaders();
            if(!accessToken.isValid()){
                System.out.println("token became invlaid, fetching new one :");
                accessToken = tokenService.getAuthToken();
            }

            headers.add("Authorization", String.valueOf("Bearer ")+accessToken.getToken());
            HttpEntity<String> reqEntity = new HttpEntity<String>("parameters", headers);

            responseEntity = requestSender.
                    exchange(uriBuilder.build().encode().toUri(),HttpMethod.GET,reqEntity,ApiDetailsResponse.class);

            try {
                responseOnEachCall = (ApiDetailsResponse) responseEntity.getBody();
            }catch (Exception e){
                e.printStackTrace();
                break;
            }

            apiDetails.getCategories().addAll(responseOnEachCall.getCategories());
            apiDetails.setCount(responseOnEachCall.getCount());
            page++;

        }while(apiDetails.getCategories().size() < apiDetails.getCount());



        return apiDetails;

    }

    public void saveApiDetailsList(List<ApiDetailEntity> apiDetailEntityList){
            apiDetailsRepository.saveAll(apiDetailEntityList);
    }




}
