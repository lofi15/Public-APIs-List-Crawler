package com.apilistcrawler.entity;

import javax.persistence.*;

@Entity
@Table(name = "api_categories")
public class ApiCategoryEntity {

    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
