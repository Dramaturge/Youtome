package com.youtome;
/**
 *我（MainActivityUserFragment）→关于Youtome
 * */
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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaeger.library.StatusBarUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.youtome.app.AppApplication;
import com.youtome.tool.PrefTools;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;
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

import static com.youtome.MainActivityHomepageApplyFragment.Fenshu;
import static com.youtome.MainActivityHomepageApplyFragment.Diqu;
import static com.youtome.MainActivityHomepageApplyFragment.Wenke;
public class RecommendResultActivity extends AppCompatActivity {

    private String Username;
    private  String Token;
    RecyclerView mRecyclerView;
    private CommonAdapter adapter_user;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recommend_result);
        StatusBarUtil.setTranslucentForCoordinatorLayout(RecommendResultActivity.this,50);

        mRecyclerView=(RecyclerView)findViewById(R.id.act_university_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecommendResultActivity.this));
        DividerItemDecoration mDecoration = new DividerItemDecoration(RecommendResultActivity.this, DividerItemDecoration.VERTICAL);
        mDecoration.setDrawable(ContextCompat.getDrawable(RecommendResultActivity.this, R.drawable.divider_bg));
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

                                    adapter_user=new CommonAdapter<University3.Detail>(RecommendResultActivity.this,
                                            R.layout.item_university, universitysuccess.results){
                                        @Override
                                        public void onBindViewHolder(ViewHolder viewHolder, int position) {
                                            super.onBindViewHolder(viewHolder, position);
                                        }
                                        @Override
                                        public void convert(final ViewHolder holder, final University3.Detail data) {
                                            TextView university = holder.getView(R.id.university);
                                            TextView location = holder.getView(R.id.location);
                                            TextView batch = holder.getView(R.id.batch);
                                            TextView belong = holder.getView(R.id.belong);
                                            final ImageView logo = holder.getView(R.id.university_logo);
                                            CardView cardView = holder.getView(R.id.cardlist_item);
                                            cardView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Uri uri = Uri.parse(data.introduction);
                                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                                    startActivity(intent);
                                                }
                                            });
                                            //  data.logo  是图片的网址
                                            university.setText(data.university);
                                            location.setText(data.location + "   " + data.type);
                                            batch.setText(data.batch + "   " + data.property);
                                            belong.setText(data.belong);
                                            DisplayImageOptions options = new DisplayImageOptions.Builder()
                                                    .showImageOnLoading(R.drawable.ic_action_write)// 设置图片下载期间显示的图片
                                                    .showImageForEmptyUri(R.drawable.ic_action_write)// 设置图片Uri为空或是错误的时候显示的图片
                                                    .showImageOnFail(R.drawable.ic_action_write)// 设置图片加载或解码过程中发生错误显示的图片
                                                    .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                                                    .cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
                                                    .displayer(new RoundedBitmapDisplayer(20))// 设置成圆角图片
                                                    .build();// 创建DisplayImageOptions对象
                                            // 使用ImageLoader加载图片
                                            imageLoader.displayImage(data.logo,
                                                    logo, options);
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
