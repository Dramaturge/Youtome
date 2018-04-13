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
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.youtome.app.AppApplication;
import com.youtome.bean.Status;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;



/**
 * Created by Chliao on 2018/3/19.
 * 朋友圈
 */

public class CirclesOfFriendsFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayoutState state;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout app_bar;
    private CommonAdapter adapter;
    private String Username;
    private  String Token;
    private ArrayList<Status> statusArrayList;
    private Button add;
    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }
    
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_main_frg_circle_of_friends ,container, false);
        add=(Button) rootView.findViewById(R.id.add);
        add.setOnClickListener(this);


        Username = PrefTools.getString(
                AppApplication.getContext(), "User","");
        Token = PrefTools.getString(
                AppApplication.getContext(), "Token","");


        statusArrayList=new ArrayList<Status>();
        statusArrayList.add(new Status(
                R.drawable.header2,
                "刘一林(老师)",
                "4-13",
                "    晚上十点了，自习室里还是坐满了人，西电的学风就是好！",
                "10",
                "2",
                "5"));
        statusArrayList.add(new Status(
                R.drawable.header3,
                "白净(老师)",
                "4-10",
                "    最近正在准备学校的数模比赛，可能没有太多时间回答学弟学妹们的问题。大家要好好学习准备考试啊！",
                "10",
                "0",
                "3"));
        statusArrayList.add(new Status(
                R.drawable.header4,
                "张丽娟(老师)",
                "4-9",
                "    成功并非终点，失败也非终结，唯有前进的勇气长存！\n",
                "10",
                "10",
                "5"));
        statusArrayList.add(new Status(
                R.drawable.header5,
                "吴佳明(老师)",
                "4-8",
                "    上一周的省检数学题，我已经看了，除了最后一道导数题有些难度以外，其他都是很基础的考点。我会在本周给大家讲一讲解题要点，敬请期待。",
                "10",
                "10",
                "5"));

        mRecyclerView= (RecyclerView) rootView.findViewById(R.id.friends_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration mDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider_bg));
        mRecyclerView.addItemDecoration(mDecoration);


        adapter=new CommonAdapter<Status>(getContext(), R.layout.status_item, statusArrayList){
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
            }

            @Override
            public void convert(ViewHolder holder, final Status data) {
                LinearLayout skip_to_others_homepage=holder.getView(R.id.skip_to_others_homepage);
                CircleImageView profile_image = holder.getView(R.id.profile_image);//头像
                TextView tv_status_publisher =holder.getView(R.id.tv_status_publisher);//发布人
                TextView tv_status_publish_time =holder.getView(R.id.tv_status_publish_time);//发布时间
                LinearLayout skip_to_detail=holder.getView(R.id.skip_to_detail);
                TextView tv_status_content =holder.getView(R.id.tv_status_content);//内容
                TextView tv_like_number =holder.getView(R.id.tv_like_number);//赞同数
                TextView tv_comments_number =holder.getView(R.id.tv_comments_number);//评论数
                TextView tv_transpond_number =holder.getView(R.id.tv_transpond_number);//转发数

                profile_image.setImageDrawable(getResources().getDrawable(data.getHeader()));
                tv_status_publisher.setText(data.getPublisher());
                tv_status_publish_time.setText(data.getPublish_time());
                tv_like_number.setText(data.getLike_number());
                tv_status_content.setText(data.getContent());
                tv_comments_number.setText(data.getComments_number());
                tv_transpond_number.setText(data.getTranspond_number());



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
                        intent.putExtra("content",data.getContent());
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
        switch (v.getId())
        {
            case R.id.add:
                Intent intent=new Intent(getContext(),SendStatusActivity.class);//跳转到发布人的个人主页
                startActivity(intent);
                break;
        }

    }

}