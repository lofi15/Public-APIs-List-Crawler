package com.apilistcrawler.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "api_details")
public class ApiDetailEntity {

    @Id
    private Integer categoryId;

    @JsonProperty(value="API")
    private String apiName;


    @JsonProperty(value="Description")
    private String description;


    @JsonProperty(value="Auth")
    private String auth;


    @JsonProperty(value="HTTPS")
    private boolean https;


    @JsonProperty(value="Cors")
    private String cors;


    @JsonProperty(value="Link")
    private String link;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public boolean isHttps() {
        return https;
    }

    public void setHttps(boolean https) {
        this.https = https;
    }

    public String getCors() {
        return cors;
    }

    public void setCors(String cors) {
        this.cors = cors;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "ApiDetailEntity{" +
                "categoryId=" + categoryId +
                ", apiName='" + apiName + '\'' +
                ", description='" + description + '\'' +
                ", auth='" + auth + '\'' +
                ", https=" + https +
                ", cors='" + cors + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
