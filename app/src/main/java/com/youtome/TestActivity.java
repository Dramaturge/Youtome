package com.youtome;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;
import com.youtome.app.AppApplication;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;
import com.youtome.view.superadapter.Search;
import com.youtome.view.superadapter.Searchfailure;
import com.youtome.view.superadapter.Searchsuccess;
import com.youtome.view.superadapter.University1;
import com.youtome.view.superadapter.University2;
import com.youtome.view.superadapter.University3;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.youtome.HomepageApplyFragment.Fenshu;
import static com.youtome.HomepageApplyFragment.Diqu;
import static com.youtome.HomepageApplyFragment.Zhuanye;
import static com.youtome.HomepageApplyFragment.Wenke;
public class TestActivity extends AppCompatActivity {

    private String Username;
    private  String Token;
    RecyclerView mRecyclerView;
    private CommonAdapter adapter_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        StatusBarUtil.setTranslucentForCoordinatorLayout(TestActivity.this,50);

        mRecyclerView=(RecyclerView)findViewById(R.id.act_university_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(TestActivity.this));
        DividerItemDecoration mDecoration = new DividerItemDecoration(TestActivity.this, DividerItemDecoration.VERTICAL);
        mDecoration.setDrawable(ContextCompat.getDrawable(TestActivity.this, R.drawable.divider_bg));
        mRecyclerView.setHasFixedSize(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);







        final TextView testdata=(TextView) findViewById(R.id.test_edit);
        Username = PrefTools.getString(
                AppApplication.getContext(), "User","");
        Token = PrefTools.getString(
                AppApplication.getContext(), "Token","");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    RequestBody requestBody =new FormBody.Builder().add("username",Username).add("token",Token).add("ssdm",Diqu).add("year","2016").add("kldm",Wenke).add("score",Fenshu).add("ranger","10").build();
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder().url("http://118.24.120.57:8080/education/queryUniversity.php").post(requestBody).build();
                    final Response response= client.newCall(request).execute();
                    final String  reponseData=response.body().string();
                    Gson gson=new Gson();
                    University1 university1=gson.fromJson(reponseData,University1.class);
                    final String status=university1.getStatus();
                    if(status.equals("fail")){
                        University2 universityfail=gson.fromJson(reponseData,University2.class);
                        final String reason= universityfail.getReason();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //UI
//                                testdata.setText(status+"对不起，未搜索到结果"+reason);
                                testdata.setText("对不起，未搜索到结果");

                            }
                        });
                    }
                    else{

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //UI
                                Gson gson = new Gson();
                                Type type = new TypeToken<University3>() {
                                }.getType();
                                University3 universitysuccess = gson.fromJson(reponseData, type);
                                List<University3.Detail> reason = universitysuccess.results;
                                int number = reason.size();
                                if (number == 0) {
                                    testdata.setText("未搜索到结果");
                                } else {
                                    testdata.setText( "   共搜索到" + number + "条 " );

                                    adapter_user=new CommonAdapter<University3.Detail>(TestActivity.this,
                                            R.layout.university_item, universitysuccess.results){
                                        @Override
                                        public void onBindViewHolder(ViewHolder viewHolder, int position) {
                                            super.onBindViewHolder(viewHolder, position);
                                        }
                                        @Override
                                        public void convert(final ViewHolder holder, final University3.Detail data) {
                                            TextView university = holder.getView(R.id.university);
                                            TextView location = holder.getView(R.id.location);
                                            TextView batch = holder.getView(R.id.batch);

                                            CardView cardView=holder.getView(R.id.cardlist_item);
                                            cardView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Uri uri = Uri.parse("https://baike.baidu.com/item/%E8%A5%BF%E5%AE%89%E7%94%B5%E5%AD%90%E7%A7%91%E6%8A%80%E5%A4%A7%E5%AD%A6/160910?fr=aladdin");
                                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                                    startActivity(intent);
                                                }
                                            });

                                            university.setText(data.getUniversity());
                                            location.setText(data.getLocation());
                                            batch.setText(data.getBatch());

                                        }
                                    };

                                    mRecyclerView.setAdapter(adapter_user);








                                }
                                    }});
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
