package com.apilistcrawler.response;

import com.apilistcrawler.entity.ApiDetailEntity;

import java.util.ArrayList;
import java.util.List;

public class ApiDetailsResponse {

    private int count;

    private List<ApiDetailEntity> categories;

    public ApiDetailsResponse(int count, List<ApiDetailEntity> categories) {
        this.count = count;
        this.categories = categories;
    }

    public ApiDetailsResponse() {
        this.count = 0;
        this.categories = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ApiDetailEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<ApiDetailEntity> categories) {
        this.categories = categories;
    }
    @Override
    public String toString() {
        return "ApiDetailsResponse{" +
                "count=" + count +
                ", categories=" + categories +
                '}';
    }

}
