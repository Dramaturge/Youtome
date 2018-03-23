package com.youtome.view.superadapter;

/**
 * Created by 10672 on 2018/3/15.
 */

public class Signup {
    private String status;
    private String reason;
    public String getStatus(){
        return status;
    }
    public String getReason(){
        return reason;
    }
    public void setSignup(String status,String reason){
        this.status=status;
        this.reason=reason;
    }
}
