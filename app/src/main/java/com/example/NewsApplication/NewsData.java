package com.example.NewsApplication;

import java.io.Serializable;

public class NewsData implements Serializable {

    private String title; // 제목
    private String urlToImage; // 뉴스 이미지 url
    private String content; // 뉴스 내용

    public String getTitle() {
        return title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
