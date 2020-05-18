package com.apilistcrawler.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApiCategoryEntity {

    private Integer categoryId;

    @Id
    private String categoryName;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
