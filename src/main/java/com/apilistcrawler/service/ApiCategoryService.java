package com.apilistcrawler.service;

import com.apilistcrawler.repository.ApiCategoryRepository;
import com.apilistcrawler.response.AccessToken;
import com.apilistcrawler.response.CategoriesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Service
public class ApiCategoryService {

    @Autowired
    private ApiCategoryRepository apiCategoryRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenService tokenService;

    @Value("${categories.url}")
    String categoriesUrl;

    public CategoriesResponse getAllApiCategories(){

        CategoriesResponse responseOnEachCall = null;
        CategoriesResponse categoriesResponse = new CategoriesResponse();

        int page=1;

        ResponseEntity responseEntity=null;

        AccessToken accessToken = tokenService.getAuthToken();

        //AccessToken accessToken =
        do{
            UriComponentsBuilder uriBuilder =  UriComponentsBuilder.fromHttpUrl(categoriesUrl);
            uriBuilder.queryParam("page",page);
            try{
                HttpHeaders headers =new HttpHeaders();
                if(!accessToken.isValid()){
                    System.out.println("token became invlaid fetching new one :");
                    accessToken = tokenService.getAuthToken();
                }

                headers.put("Authorization", Arrays.asList(String.valueOf("Bearer ")+accessToken.getToken()));
                HttpEntity<String> reqEntity = new HttpEntity<String>("parameters", headers);

                responseEntity = restTemplate.
                                  exchange(uriBuilder.build().encode().toUri(), HttpMethod.GET,reqEntity,CategoriesResponse.class);

                System.out.println("api call made, response :"+responseEntity.getBody().toString());


            }catch (HttpClientErrorException.TooManyRequests exception){
                System.out.println("sleeping");
                try {
                    Thread.sleep(60000);
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
//            System.out.println("currPage :"+page);
            responseOnEachCall = (CategoriesResponse) responseEntity.getBody();
            categoriesResponse.getCategories().addAll(responseOnEachCall.getCategories());
            categoriesResponse.setCount(responseOnEachCall.getCount());
            page++;

        }while(responseOnEachCall.getCategories().size() !=0 );

//        System.out.println(categoriesResponse);

        return categoriesResponse;

    }




}
