package com.apilistcrawler;

import com.apilistcrawler.response.AccessToken;
import com.apilistcrawler.response.ApiDetailsResponse;
import com.apilistcrawler.response.CategoriesResponse;
import com.apilistcrawler.service.ApiCategoryService;
import com.apilistcrawler.service.ApiDetailService;
import com.apilistcrawler.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApiListCrawlerApplication {

    @Bean
    public RestTemplate getRestTempplate(){
        return new RestTemplate();
    }
    @Autowired
    TokenService tokenService;

    @Autowired
    private ApiCategoryService apiCategoryService;

    @Autowired
    private ApiDetailService apiDetailService;


    public static void main(String[] args) {
        SpringApplication.run(ApiListCrawlerApplication.class, args);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

//        System.out.println("call Made");
//        CategoriesResponse categoriesResponse = apiCategoryService.getAllApiCategories();
//        System.out.println("response received !!");
        System.out.println("start");
        ApiDetailsResponse response = apiDetailService.getAllApiDetails("Art & Design");
        System.out.println("finished !!!");

    }
}
