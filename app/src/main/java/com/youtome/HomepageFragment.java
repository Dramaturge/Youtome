package com.youtome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youtome.bean.Question;
import com.youtome.view.ClearEditText;
import  com.youtome.view.superadapter.*;
import com.youtome.view.SlideShowView.SlideShowView;

import java.util.ArrayList;
import java.util.List;

/***
 *主页Fragment
 */

public class HomepageFragment extends Fragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private SuperAdapter adapter;
    private View rootView = null;//缓存Fragment view
    private List<LayoutWrapper> data;
    private SwipeRefreshLayout lay_fresh;

    private class Title{
        String title;
        Title(String title){
            this.title=title;
        }
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
    private class Top{
        String title;
        String brief;
        Top(String title, String brief){
            this.title=title;
            this.brief=brief;
        }
        public String getTitle() {
            return title;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
    private class Senior{}

    private DataHolder<Top> topDataHolder;
    private DataHolder<Title>titleDataHolder;
    private DataHolder<Senior> seniorDataHolder;

    private SlideShowView slideShowView;
    private ClearEditText clearEditText;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.act_main_frg_homepage, container, false);


        slideShowView=(SlideShowView)this.rootView.findViewById(R.id.homepage_slide_show);
        String img = "http://pic16.nipic.com/20110921/7247268_215811562102_2.jpg";
        String[] imgs= new String[]{img,img,img,img,img,img,img};
        slideShowView.setImageUrls(imgs);
        slideShowView.startPlay();

        clearEditText=(ClearEditText)this.rootView.findViewById(R.id.clearEditText);
        clearEditText.setCursorVisible(false);
        clearEditText.setFocusable(false);
        clearEditText.setFocusableInTouchMode(false);
        clearEditText.setOnClickListener(this);

        recyclerView= (RecyclerView) this.rootView.findViewById(R.id.homepage_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        int[] layoutIds={
                R.layout.fragment_homepage_topitem,//置顶项
                R.layout.homepage_head,//标题项
                R.layout.senior_item

        };


        adapter = new SuperAdapter(getContext(), layoutIds);
        recyclerView.setAdapter(adapter);
        data = new ArrayList<>();





        topDataHolder=new DataHolder<Top>() {
            @Override
            public void bind(Context context, SuperViewHolder holder, Top item, int position) {
                final TextView top_title=  holder.getView(R.id.tv_stick_title);
                final TextView top_brief=  holder.getView(R.id.tv_stick_brief);
                top_title.setText(item.getTitle());
                top_brief.setText(item.getBrief());
            }
        };


        titleDataHolder=new DataHolder<Title>(){
            @Override
            public void bind(Context context, SuperViewHolder holder, Title item, int position) {
                final TextView find_question_title=  holder.getView(R.id.homepage_title_tv);
                find_question_title.setText(item.getTitle());
            }
        };

        seniorDataHolder=new DataHolder<Senior>(){
            @Override
            public void bind(Context context, SuperViewHolder holder, Senior item, int position) {
            }
        };

        /**
         * 数据加载
         */
        data.add(new LayoutWrapper(R.layout.fragment_homepage_topitem, new Top("彼端上线了","    感谢大家的支持！" ), topDataHolder));

        data.add(new LayoutWrapper(R.layout.homepage_head, new Title("优秀学长学姐"), titleDataHolder));
        data.add(new LayoutWrapper(R.layout.senior_item, new Senior(), seniorDataHolder));
        data.add(new LayoutWrapper(R.layout.senior_item, new Senior(), seniorDataHolder));
        data.add(new LayoutWrapper(R.layout.senior_item, new Senior(), seniorDataHolder));


        adapter.setData(data);
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






