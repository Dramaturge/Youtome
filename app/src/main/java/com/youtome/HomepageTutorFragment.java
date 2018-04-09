package com.youtome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youtome.bean.Status;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Chliao on 2018/3/19.
 * 主页 →家教
 */

public class HomepageTutorFragment extends Fragment implements View.OnClickListener {


    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayoutState state;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout app_bar;
    private CommonAdapter adapter;

    private android.widget.LinearLayout yuwen;
    private android.widget.LinearLayout shuxue;
    private android.widget.LinearLayout yingyu;
    private android.widget.LinearLayout wuli;
    private android.widget.LinearLayout huaxue;
    private android.widget.LinearLayout shengwu;
    private android.widget.LinearLayout lishi;
    private android.widget.LinearLayout zhengzhi;
    private android.widget.LinearLayout dili;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }


    private ArrayList<Status> statusArrayList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_main_frg_homepage_tutor, container, false);

        this.dili = (LinearLayout) rootView.findViewById(R.id.dili);
        this.zhengzhi = (LinearLayout) rootView.findViewById(R.id.zhengzhi);
        this.lishi = (LinearLayout) rootView.findViewById(R.id.lishi);
        this.shengwu = (LinearLayout) rootView.findViewById(R.id.shengwu);
        this.huaxue = (LinearLayout) rootView.findViewById(R.id.huaxue);
        this.wuli = (LinearLayout) rootView.findViewById(R.id.wuli);
        this.yingyu = (LinearLayout) rootView.findViewById(R.id.yingyu);
        this.shuxue = (LinearLayout) rootView.findViewById(R.id.shuxue);
        this.yuwen = (LinearLayout) rootView.findViewById(R.id.yuwen);

        app_bar=(AppBarLayout)rootView.findViewById(R.id.app_bar);
        collapsingToolbarLayout=(CollapsingToolbarLayout)rootView.findViewById(R.id.tool_bar_layout);

        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        collapsingToolbarLayout.setTitle("");
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state !=CollapsingToolbarLayoutState.COLLAPSED) {
                        collapsingToolbarLayout.setTitle("课程推荐");//设置title不显示
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if(state == CollapsingToolbarLayoutState.COLLAPSED){
                        }
                        collapsingToolbarLayout.setTitle("");
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });




        statusArrayList=new ArrayList<Status>();
        statusArrayList.add(new Status());
        statusArrayList.add(new Status());
        statusArrayList.add(new Status());
        statusArrayList.add(new Status());
        statusArrayList.add(new Status());

        mRecyclerView= (RecyclerView) rootView.findViewById(R.id.tutor_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration mDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider_bg));
        mRecyclerView.addItemDecoration(mDecoration);


        adapter=new CommonAdapter<Status>(getContext(), R.layout.article_item, statusArrayList){
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
                TextView tv_status_likes =holder.getView(R.id.tv_status_likes);//赞同数

                skip_to_others_homepage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getContext(),OtherHomepageActivity.class);//跳转到发布人的个人主页
                        startActivity(intent);
                    }
                });


                skip_to_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getContext(),DetailActivity.class);//跳转到这条状态的详细内容
                        startActivity(intent);
                    }
                });


            }


        };

        mRecyclerView.setAdapter(adapter);

        return rootView;

    }
    @Override
    public void onClick(View v) {

    }
}