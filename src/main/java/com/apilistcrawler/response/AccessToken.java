package com.apilistcrawler.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken {

    private String token;

    private LocalDateTime expirationDateAndTime;

    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationDateAndTime() {
        return expirationDateAndTime;
    }

    public void setExpirationDateAndTime(LocalDateTime expirationDateAndTime) {
        this.expirationDateAndTime = expirationDateAndTime;
    }

    public boolean isValid(){
        return LocalDateTime.now().isBefore(expirationDateAndTime);
    }

    @Override
    public String toString(){
        return String.valueOf(token+" ,"+ expirationDateAndTime.toString());
    }


}
