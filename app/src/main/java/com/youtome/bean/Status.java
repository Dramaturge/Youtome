package com.youtome.bean;

/**
 * Created by Chliao on 2018/3/21.
 */

public class Status {
    int header;
    String publisher;
    String publish_time;
    String content;
    String like_number;
    String transpond_number;
    String comments_number;
    String id;
    public Status(){

    }
    public Status(int header,String publisher,String publish_time,
                  String content,String like_number,String transpond_number,
                  String comments_number,String id){
        this.header=header;
        this.publisher=publisher;
        this.publish_time=publish_time;
        this.content=content;
        this.like_number=like_number;
        this.transpond_number=transpond_number;
        this.comments_number=comments_number;
        this.header=header;
        this.id=id;
    }
    public String getId() {
        return id;
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

    public String getContent() {
        return content;
    }

    public String getLike_number() {
        return like_number;
    }

    public String getTranspond_number() {
        return transpond_number;
    }

    public String getComments_number() {
        return comments_number;
    }
}
