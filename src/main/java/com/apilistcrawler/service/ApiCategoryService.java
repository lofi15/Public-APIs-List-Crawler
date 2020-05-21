package com.apilistcrawler.service;

import com.apilistcrawler.entity.ApiCategoryEntity;
import com.apilistcrawler.repository.ApiCategoryRepository;
import com.apilistcrawler.response.AccessToken;
import com.apilistcrawler.response.CategoriesResponse;
import com.apilistcrawler.sender.RequestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class ApiCategoryService {

    @Autowired
    private ApiCategoryRepository apiCategoryRepository;

    @Autowired
    private RequestSender requestSender;

    @Autowired
    private TokenService tokenService;

    @Value("${categories.url}")
    private String categoriesUrl;

    public CategoriesResponse getAllApiCategories(){

        CategoriesResponse responseOnEachCall = null;
        CategoriesResponse categoriesResponse = new CategoriesResponse();

        int page=1;

        ResponseEntity responseEntity=null;

        AccessToken accessToken = tokenService.getAuthToken();

        do{

            UriComponentsBuilder uriBuilder =  UriComponentsBuilder.fromHttpUrl(categoriesUrl);
            uriBuilder.queryParam("page",page);


            HttpHeaders headers =new HttpHeaders();
            if(!accessToken.isValid()){
                accessToken = tokenService.getAuthToken();
            }
            headers.put("Authorization", Arrays.asList(String.valueOf("Bearer ")+accessToken.getToken()));
            HttpEntity<String> reqEntity = new HttpEntity<String>("parameters", headers);

            responseEntity = requestSender.
                              exchange(uriBuilder.build().encode().toUri(), HttpMethod.GET,reqEntity,CategoriesResponse.class);

            try{
                responseOnEachCall = (CategoriesResponse) responseEntity.getBody();
            }catch (Exception e){
                e.printStackTrace();
                break;
            }


            categoriesResponse.getCategories().addAll(responseOnEachCall.getCategories());
            categoriesResponse.setCount(responseOnEachCall.getCount());
            page++;

        }while(categoriesResponse.getCategories().size() < categoriesResponse.getCount() );

        return categoriesResponse;

    }

    public void saveApiCategories(List<ApiCategoryEntity> apiCategoryEntityList){

        apiCategoryRepository.saveAll(apiCategoryEntityList);

    }








}
