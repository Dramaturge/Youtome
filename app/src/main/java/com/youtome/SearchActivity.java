package com.youtome;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youtome.app.AppApplication;
import com.youtome.tool.PrefTools;
import com.youtome.view.ClearEditText;


import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;
import com.jaeger.library.StatusBarUtil;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import com.youtome.view.superadapter.Search;
import com.youtome.view.superadapter.Searchfailure;
import com.youtome.view.superadapter.Searchsuccess;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



/**
 * 搜索页面
 * */
public class SearchActivity extends AppCompatActivity{




    ClearEditText mClearEditText;
    QMUIGroupListView mAboutGroupListView;
    RecyclerView mRecyclerView;
    private CommonAdapter adapter_user;
    private CommonAdapter adapter_class;

    private String Username;
    private  String Token;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search);
        StatusBarUtil.setTranslucentForCoordinatorLayout(SearchActivity.this,50);

        Username = PrefTools.getString(
                AppApplication.getContext(), "User","");
        Token = PrefTools.getString(
                AppApplication.getContext(), "Token","");
//        StatusBarUtil.setColor(SearchActivity.this, getResources().getColor(R.color.shallowGray), 100);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("搜索");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mClearEditText=(ClearEditText)findViewById(R.id.clearEditText);
        mAboutGroupListView=(QMUIGroupListView)findViewById(R.id.about_list);
        mRecyclerView=(RecyclerView)findViewById(R.id.act_search_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        DividerItemDecoration mDecoration = new DividerItemDecoration(SearchActivity.this, DividerItemDecoration.VERTICAL);
        mDecoration.setDrawable(ContextCompat.getDrawable(SearchActivity.this, R.drawable.divider_bg));
        mRecyclerView.setHasFixedSize(true);



        // 搜索框的键盘搜索键点击回调
        mAboutGroupListView.setVisibility(View.GONE);
        mClearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mAboutGroupListView.getVisibility()==View.GONE){
                    mRecyclerView.setAdapter(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() == 0) {
                    mAboutGroupListView.setVisibility(View.GONE);
                } else {
                    mAboutGroupListView.setVisibility(View.VISIBLE);
                }
            }
        });


        QMUIGroupListView.newSection(SearchActivity.this)
                .addItemView(mAboutGroupListView.createItemView("按学科搜索"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String Edit=mClearEditText.getText().toString();
                        doSearch(Edit);
                    }
                })
                .addItemView(mAboutGroupListView.createItemView("按地区搜索"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String Edit=mClearEditText.getText().toString();
                        doSearch(Edit);
                    }
                })
                .addItemView(mAboutGroupListView.createItemView("按学校搜索"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String Edit=mClearEditText.getText().toString();
                        doSearch(Edit);
                    }
                })
                .addTo(mAboutGroupListView);


        intent=getIntent();
        if(intent.getBooleanExtra("auto",false)){
            doSearch(intent.getStringExtra("subject"));
        }

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

    //进行搜索
    private void doSearch(final String Edit){
        mAboutGroupListView.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    RequestBody requestBody =new FormBody.Builder().add("username",Username).add("token",Token).add("searchStr", Edit).build();
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder().url("http://118.24.120.57:8080/education/search.php").post(requestBody).build();
                    final Response response= client.newCall(request).execute();
                    final String  reponseData=response.body().string();
                    Gson gson=new Gson();
                    Search search=gson.fromJson(reponseData,Search.class);
                    final String status=search.getStatus();
                    if(status.equals("fail")){
                        Searchfailure searchfail=gson.fromJson(reponseData,Searchfailure.class);
                        final String reason= searchfail.getReason();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //UI
//                                                text.setText(status+"未搜索到结果"+reason);
                                Toast.makeText(SearchActivity.this, "对不起，没有找到搜索结果", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else{

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //UI
                                Gson gson=new Gson();
                                Type type=new TypeToken<Searchsuccess>(){}.getType();
                                Searchsuccess searchsuccess=gson.fromJson(reponseData,type);
                                List<Searchsuccess.Detail> reason = searchsuccess.results;
                                int number=reason.size();
                                if(number==0){
                                    Toast.makeText(SearchActivity.this, "对不起，没有找到搜索结果", Toast.LENGTH_SHORT).show();
                                }else {


                                    adapter_user=new CommonAdapter<Searchsuccess.Detail>(SearchActivity.this, R.layout.item_user, searchsuccess.results){
                                        @Override
                                        public void onBindViewHolder(ViewHolder viewHolder, int position) {
                                            super.onBindViewHolder(viewHolder, position);
                                        }
                                        @Override
                                        public void convert(final ViewHolder holder, final Searchsuccess.Detail user) {
                                            TextView name = holder.getView(R.id.name);
                                            TextView grade = holder.getView(R.id.grade);
                                            TextView school = holder.getView(R.id.school);
                                            TextView college = holder.getView(R.id.college);
                                            TextView subject = holder.getView(R.id.subject);
                                            TextView experience = holder.getView(R.id.experience);
                                            TextView motto = holder.getView(R.id.motto);
                                            TextView style = holder.getView(R.id.style);
                                            CardView cardView=holder.getView(R.id.cardlist_item);
                                            cardView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent=new Intent(SearchActivity.this,OtherHomepageActivity.class);//跳转到发布人的个人主页
                                                    startActivity(intent);
                                                }
                                            });

                                            name.setText(user.getName());
                                            grade.setText(user.getGrade());
                                            school.setText(user.getSchool());
                                            college.setText(user.getCollege());
                                            subject.setText(user.getSubject());
                                            experience.setText(user.getExperience());
                                            motto.setText(user.getMotto());
                                            style.setText(user.getStyle());
                                        }
                                    };

                                    mRecyclerView.setAdapter(adapter_user);
                                }

                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }




}
