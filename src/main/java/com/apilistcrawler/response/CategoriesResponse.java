package com.apilistcrawler.response;

import java.util.ArrayList;
import java.util.List;

public class CategoriesResponse {

    private int count;

    private List<String> categories;

    public CategoriesResponse(int count, List<String> categories) {
        this.count = count;
        this.categories = categories;
    }

    public CategoriesResponse() {
        this.count = 0;
        categories = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String toString(){

       return  String.valueOf(count)+" "+categories.toString();

    }


}
