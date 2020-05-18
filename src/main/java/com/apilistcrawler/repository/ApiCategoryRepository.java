package com.apilistcrawler.repository;

import com.apilistcrawler.entity.ApiCategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApiCategoryRepository extends PagingAndSortingRepository<ApiCategoryEntity,String> {



}
