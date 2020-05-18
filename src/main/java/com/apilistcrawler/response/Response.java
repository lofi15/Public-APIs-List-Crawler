package com.apilistcrawler.response;

public class Response<T> {

    private int count;

    private T categories;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getCategories() {
        return categories;
    }

    public void setCategories(T categories) {
        this.categories = categories;
    }
}

