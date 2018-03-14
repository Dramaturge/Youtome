package com.youtome;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import com.youtome.view.ClearEditText;

import com.youtome.bean.SearchUser;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;
import com.jaeger.library.StatusBarUtil;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import java.util.ArrayList;
import com.youtome.R;

/**
 * 搜索页面

 * */
public class SearchActivity extends AppCompatActivity{


    ClearEditText mClearEditText;
    QMUIGroupListView mAboutGroupListView;
    RecyclerView mRecyclerView;
    private CommonAdapter adapter_user;
    private CommonAdapter adapter_class;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search_fragment_search);
        StatusBarUtil.setColor(SearchActivity.this, getResources().getColor(R.color.shallowGray), 50);
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

        ArrayList<SearchUser> checksArrayList=new ArrayList<SearchUser>();
        checksArrayList.add(new SearchUser());

        adapter_user=new CommonAdapter<SearchUser>(SearchActivity.this, R.layout.user_item, checksArrayList){
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
            }
            @Override
            public void convert(ViewHolder holder, SearchUser user) {
                holder.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        };

        adapter_class=new CommonAdapter<SearchUser>(SearchActivity.this, R.layout.class_item, checksArrayList){
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
            }

            @Override
            public void convert(ViewHolder holder, SearchUser user) {


                holder.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        };

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
                .addItemView(mAboutGroupListView.createItemView("按专业搜索"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAboutGroupListView.setVisibility(View.GONE);
//                        mRecyclerView.setAdapter(adapter_user);
                    }
                })
                .addItemView(mAboutGroupListView.createItemView("按地区搜索"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAboutGroupListView.setVisibility(View.GONE);

                    }
                })
                .addItemView(mAboutGroupListView.createItemView("按学校搜索"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAboutGroupListView.setVisibility(View.GONE);
                    }
                })
                .addTo(mAboutGroupListView);

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
