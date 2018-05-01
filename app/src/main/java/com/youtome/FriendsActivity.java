package com.youtome;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;
import com.youtome.view.superadapter.Search;
import com.youtome.view.superadapter.Searchsuccess;
import com.youtome.view.superadapter.University4;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private CommonAdapter adapter;
    class Friends{}
    private ArrayList<Friends> friendsArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_friends);
        StatusBarUtil.setTranslucentForCoordinatorLayout(FriendsActivity.this,50);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView=(RecyclerView)findViewById(R.id.act_friends_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(FriendsActivity.this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        DividerItemDecoration mDecoration = new DividerItemDecoration(FriendsActivity.this, DividerItemDecoration.VERTICAL);
        mDecoration.setDrawable(ContextCompat.getDrawable(FriendsActivity.this, R.drawable.divider_bg));
        mRecyclerView.setHasFixedSize(true);

        friendsArrayList=new ArrayList<Friends>();
        friendsArrayList.add(new Friends());
        friendsArrayList.add(new Friends());
        friendsArrayList.add(new Friends());


        adapter=new CommonAdapter<Friends>(FriendsActivity.this, R.layout.item_senior, friendsArrayList){
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
            }
            @Override
            public void convert(final ViewHolder holder, final Friends friends) {

            }
        };
        mRecyclerView.setAdapter(adapter);

    }

    //生成菜单
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.add_icon:
                Intent intent = new Intent(FriendsActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
