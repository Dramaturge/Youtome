package com.youtome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youtome.bean.Status;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;
import  com.youtome.view.superadapter.*;
import com.youtome.view.SlideShowView.SlideShowView;

import java.util.ArrayList;
import java.util.List;

/***
 *主页Fragment
 */

public class HomepageConsultationFragment extends Fragment implements View.OnClickListener{
    private RecyclerView mRecyclerView;
    private  CommonAdapter adapter;

    private View rootView = null;//缓存Fragment view
    private List<LayoutWrapper> data;
    private SwipeRefreshLayout lay_fresh;

    private  ArrayList<Status> statusArrayList;

    private SlideShowView slideShowView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.act_main_frg_homepage_consultation, container, false);


//        slideShowView=(SlideShowView)this.rootView.findViewById(R.id.homepage_slide_show);
//        String img = "http://pic16.nipic.com/20110921/7247268_215811562102_2.jpg";
//        String[] imgs= new String[]{img,img,img,img,img,img,img};
//        slideShowView.setImageUrls(imgs);
//        slideShowView.startPlay();

        statusArrayList=new ArrayList<Status>();
        statusArrayList.add(new Status());
        statusArrayList.add(new Status());
        statusArrayList.add(new Status());
        statusArrayList.add(new Status());
        statusArrayList.add(new Status());

        mRecyclerView= (RecyclerView) this.rootView.findViewById(R.id.homepage_recycler);
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
        switch (v.getId())
        {
            case R.id.clearEditText:
                Intent intent=new Intent(getContext(),SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

}






