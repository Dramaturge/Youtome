package com.youtome.bean;
public class Article {
    int header;
    String publisher;
    String publish_time;
    String title;
    String content;
    String like_number;

    public Article(){}
    public Article(int header,String publisher,String publish_time,String title,String content,String like_number){
        this.header=header;
        this.publisher=publisher;
        this.publish_time=publish_time;
        this.title=title;
        this.content=content;
        this.like_number=like_number;
    }


    public int getHeader() {
        return header;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLike_number() {
        return like_number;
    }
}
