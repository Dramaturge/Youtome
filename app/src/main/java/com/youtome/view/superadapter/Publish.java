package com.youtome.view.superadapter;

/**
 * Created by 10672 on 2018/4/12.
 */

public class Publish {
    private String status;
    private String reason;

    public String getStatus(){
        return status;
    }
    public String getReason(){
        return reason;
    }

    public void setPubish(String status,String reason){
        this.status=status;
        this.reason=reason;

    }
}
