package com.youtome.view.superadapter;

/**
 * Created by 10672 on 2018/3/16.
 */

public class Signin {
    private String status;
    private String reason;
    private String token;
    public String getStatus(){
        return status;
    }
    public String getReason(){
        return reason;
    }
    public String getToken()  {return token;}
    public void setSignup(String status,String reason,String token){
        this.status=status;
        this.reason=reason;
        this.token=token;
    }
}
