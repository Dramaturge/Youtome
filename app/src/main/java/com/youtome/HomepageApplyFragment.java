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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.youtome.bean.Status;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Chliao on 2018/3/19.
 * 主页→报考
 */


public class HomepageApplyFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayoutState state;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout app_bar;
    private  CommonAdapter adapter;

    private ArrayList<Status> statusArrayList;
    private com.rengwuxian.materialedittext.MaterialEditText met_score;
    private com.rengwuxian.materialedittext.MaterialEditText met_region;
    private com.rengwuxian.materialedittext.MaterialEditText met_major;
    private android.widget.RadioButton rb_arts;
    private android.widget.RadioButton rb_sciences;
    private android.widget.RadioGroup rg_subject;
    private com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton btn_query;



    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_main_frg_homepage_apply, container, false);
        this.btn_query = (QMUIRoundButton) rootView.findViewById(R.id.btn_query);//查询按钮
        this.rg_subject = (RadioGroup) rootView.findViewById(R.id.rg_subject);
        this.rb_sciences = (RadioButton) rootView.findViewById(R.id.rb_sciences);//理科;
        this.rb_arts = (RadioButton) rootView.findViewById(R.id.rb_arts);//文科
        this.met_major = (MaterialEditText) rootView.findViewById(R.id.met_major);//专业
        this.met_region = (MaterialEditText) rootView.findViewById(R.id.met_region);//地区
        this.met_score = (MaterialEditText) rootView.findViewById(R.id.met_score);//分数
        //MaterialEditText用法和普通的EditText完全相同

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
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        collapsingToolbarLayout.setTitle("报考推荐");//设置title不显示
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

        mRecyclerView= (RecyclerView) rootView.findViewById(R.id.apply_recycler);
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
                TextView tv_status_brief =holder.getView(R.id.tv_status_content);//内容
                TextView tv_status_likes =holder.getView(R.id.tv_status_likes);//赞同数

                skip_to_others_homepage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getContext(),OtherHomepageActivity.class);//跳转到发布人的个人主页
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
