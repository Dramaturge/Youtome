package com.youtome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.youtome.app.AppApplication;
import com.youtome.tool.PrefTools;
import com.youtome.view.superadapter.Publish;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendStatusActivity extends AppCompatActivity {
    private com.rengwuxian.materialedittext.MaterialEditText thing;
    private String Username;
    private String Token;
   private String mcontent;
   private String mname;
   private String id;
   private  String reason;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent=getIntent();
        super.onCreate(savedInstanceState);
        mcontent=intent.getStringExtra("content");
        mname=intent.getStringExtra("name");
        id=intent.getStringExtra("id");
        reason =intent.getStringExtra("reason");

        Username = PrefTools.getString(
                AppApplication.getContext(), "User","");
        Token = PrefTools.getString(
                AppApplication.getContext(), "Token","");

        setContentView(R.layout.act_send_status);
        StatusBarUtil.setTranslucentForCoordinatorLayout(SendStatusActivity.this,50);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        thing=(com.rengwuxian.materialedittext.MaterialEditText) findViewById(R.id.met_thing);
        if(id==null){
            id="-1";
            mname=Username;
        }
        else{

            thing.setText("转自:"+mname+"     理由:"+reason+"      内容:"+mcontent);
        }
    }



    //生成菜单
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tick_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.tick_icon:


                final String Thing=thing.getText().toString();



                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            RequestBody requestBody =new FormBody.Builder().add("username",Username).add("token",Token).add("title", "动态").add("content",Thing).add("type","3").add("sourcer",mname).add("id",id).build();
                            OkHttpClient client=new OkHttpClient();
                            Request request=new Request.Builder().url("http://118.24.120.57:8080/education/publishArticle.php").post(requestBody).build();
                            final Response response= client.newCall(request).execute();
                            final String  reponseData=response.body().string();
                            Gson gson=new Gson();
                            Publish publish=gson.fromJson(reponseData,Publish.class);
                            final String status=publish.getStatus();
                            if(status.equals("fail")){

                                final String reason=publish.getReason();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Toast.makeText(SendStatusActivity.this, "发表失败 "+reason, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(SendStatusActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                                    }

                                });

                                finish();

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
