package com.youtome;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.youtome.bean.Status;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;

import java.util.ArrayList;

/**
 * Created by Chliao on 2018/3/19.
 */


public class HomepageApplyFragment extends Fragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayoutState state;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout app_bar;
    private  CommonAdapter adapter;

    private ArrayList<Status> statusArrayList;


    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_main_frg_homepage_apply ,container, false);

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


        adapter=new CommonAdapter<Status>(getContext(), R.layout.status_item, statusArrayList){
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
            }

            @Override
            public void convert(ViewHolder holder, Status categoryChapter) {

            }
        };

        mRecyclerView.setAdapter(adapter);

        return rootView;
    }
    @Override
    public void onClick(View v) {

    }
}
