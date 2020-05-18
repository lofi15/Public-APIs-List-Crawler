package com.apilistcrawler.service;

import com.apilistcrawler.entity.ApiCategoryEntity;
import com.apilistcrawler.entity.ApiDetailEntity;
import com.apilistcrawler.response.ApiDetailsResponse;
import com.apilistcrawler.response.CategoriesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlerService {

    @Autowired
    private ApiCategoryService apiCategoryService;

    @Autowired
    private ApiDetailService apiDetailService;


    public void crawlData(){

        CategoriesResponse allCategories = apiCategoryService.getAllApiCategories();

        List<ApiCategoryEntity> apiCategoryEntityList = new ArrayList<>();

        allCategories.getCategories().stream().forEach(c->{
            apiCategoryEntityList.add(new ApiCategoryEntity(c));
        } );

        apiCategoryService.saveApiCategories(apiCategoryEntityList);

        apiCategoryEntityList.stream().forEach((apiCategoryEntity)->{
            ApiDetailsResponse response = apiDetailService.
                    getAllApiDetails(apiCategoryEntity.getCategoryName());

            response.getCategories().stream().forEach(apiDetailEntity -> {
                apiDetailEntity.setCategoryId(apiCategoryEntity.getCategoryId());
            });

            apiDetailService.saveApiDetailsList(response.getCategories());

        });


    }




}
