package com.apilistcrawler.service;

import com.apilistcrawler.entity.ApiCategoryEntity;
import com.apilistcrawler.entity.ApiDetailEntity;
import com.apilistcrawler.response.ApiDetailsResponse;
import com.apilistcrawler.response.CategoriesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger logger = LoggerFactory.getLogger(CrawlerService.class);

    public void crawlData(){

        logger.info("fetching all categories");
        CategoriesResponse allCategories = apiCategoryService.getAllApiCategories();

        List<ApiCategoryEntity> apiCategoryEntityList = new ArrayList<>();

        allCategories.getCategories().stream().forEach(c->{
            apiCategoryEntityList.add(new ApiCategoryEntity(c));
        } );
        logger.info("saving all categories");
        apiCategoryService.saveApiCategories(apiCategoryEntityList);

        logger.info("fetching and saving all apiDetails");
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
