package com.youtome;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.youtome.bean.Status;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;
import com.youtome.view.SlideShowView.SlideShowView;
import com.youtome.view.superadapter.LayoutWrapper;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OtherHomepageActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayoutState state;
    private  CommonAdapter adapter;
    private List<LayoutWrapper> data;
    private SwipeRefreshLayout lay_fresh;
    private  ArrayList<Status> statusArrayList;
    private SlideShowView slideShowView;


    private ImageView collapsing_bar_background_image;
    private Toolbar user_tool_bar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout app_bar;
    private CircleImageView profile_image;
    private TextView user_name;
    private TextView personal_signal;
    private TextView personal_brief;
    private TextView focus_count;
    private TextView fans_count;
    private TextView read_count_text;
    private TextView likes_count;
    private LinearLayout various_counts_layout;
    private RecyclerView status_recycler;
    private LinearLayout user_information_all;
    private NestedScrollView scroll_view;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_other_homepage);
        StatusBarUtil.setTranslucentForCoordinatorLayout(OtherHomepageActivity.this,50);

        initView();

    }

    private void initView() {
        collapsing_bar_background_image = (ImageView) findViewById(R.id.collapsing_bar_background_image);
        user_tool_bar = (Toolbar) findViewById(R.id.user_tool_bar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.tool_bar_layout);
        app_bar = (AppBarLayout) findViewById(R.id.app_bar);
        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        user_name = (TextView) findViewById(R.id.user_name);
        personal_signal = (TextView) findViewById(R.id.personal_signal);
        personal_brief = (TextView) findViewById(R.id.personal_brief);
        focus_count = (TextView) findViewById(R.id.focus_count);
        fans_count = (TextView) findViewById(R.id.fans_count);
        read_count_text = (TextView) findViewById(R.id.read_count_text);
        likes_count = (TextView) findViewById(R.id.likes_count);
        various_counts_layout = (LinearLayout) findViewById(R.id.various_counts_layout);
        user_information_all = (LinearLayout) findViewById(R.id.user_information_all);
        scroll_view = (NestedScrollView) findViewById(R.id.scroll_view);
        setSupportActionBar(user_tool_bar);
        getSupportActionBar().setTitle("");


        statusArrayList=new ArrayList<Status>();
        statusArrayList.add(new Status());
        statusArrayList.add(new Status());
        statusArrayList.add(new Status());
        statusArrayList.add(new Status());
        statusArrayList.add(new Status());

        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        profile_image.setVisibility(View.VISIBLE);
                        collapsingToolbarLayout.setTitle("");
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                        profile_image.setVisibility(View.INVISIBLE);
                        getSupportActionBar().setTitle("游世隆的主页");
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if(state == CollapsingToolbarLayoutState.COLLAPSED){
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                        profile_image.setVisibility(View.VISIBLE);
                        collapsingToolbarLayout.setTitle("");

                    }
                }
            }
        });
        
        
        mRecyclerView = (RecyclerView) findViewById(R.id.status_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration mDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_bg));
        mRecyclerView.addItemDecoration(mDecoration);


        adapter=new CommonAdapter<Status>(this, R.layout.status_item, statusArrayList){
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
            }

            @Override
            public void convert(ViewHolder holder, Status categoryChapter) {
                LinearLayout skip_to_others_homepage=holder.getView(R.id.skip_to_others_homepage);
                CircleImageView profile_image = holder.getView(R.id.profile_image);//头像
                TextView tv_status_publisher =holder.getView(R.id.tv_status_publisher);//发布人
                TextView tv_status_publish_time =holder.getView(R.id.tv_status_publish_time);//发布时间
                LinearLayout skip_to_detail=holder.getView(R.id.skip_to_detail);
                TextView tv_status_title =holder.getView(R.id.tv_status_title);//标题
                TextView tv_status_brief =holder.getView(R.id.tv_status_brief);//简介
                TextView tv_like_number =holder.getView(R.id.tv_like_number);//赞同数
                TextView tv_comments_number =holder.getView(R.id.tv_comments_number);//评论数
                TextView tv_transpond_number =holder.getView(R.id.tv_transpond_number);//转发数

                skip_to_others_homepage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(OtherHomepageActivity.this,OtherHomepageActivity.class);//跳转到发布人的个人主页
                        startActivity(intent);
                    }
                });


                skip_to_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(OtherHomepageActivity.this,DetailActivity.class);//跳转到这条状态的详细内容
                        startActivity(intent);
                    }
                });


            }


        };


        mRecyclerView.setAdapter(adapter);


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
