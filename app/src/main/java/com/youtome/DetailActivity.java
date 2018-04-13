package com.youtome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.youtome.view.SelectableTextView;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SelectableTextView read_pv_page;
    private LinearLayout read_dl_slide;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_detail);
        StatusBarUtil.setTranslucentForCoordinatorLayout(DetailActivity.this,50);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        read_pv_page = (SelectableTextView) findViewById(R.id.read_pv_page);
        read_dl_slide = (LinearLayout) findViewById(R.id.read_dl_slide);
        title=(TextView)findViewById(R.id.detail_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        String content=intent.getStringExtra("content");
        String mtitle=intent.getStringExtra("title");
        if(mtitle==null){
            title.setText("详情");
        }else{
            title.setText(mtitle);
        }
        read_pv_page.setText(content);

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
