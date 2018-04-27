package com.youtome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

/**
 *我（MainActivityUserFragment）→账号与安全
 * */

public class AccountActivity extends AppCompatActivity {
    private Toolbar toolbar;
    QMUIGroupListView mGroupListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_account);
        StatusBarUtil.setTranslucentForCoordinatorLayout(AccountActivity.this,50);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mGroupListView=(QMUIGroupListView)findViewById(R.id.groupListView);
        initGroupListView();

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

    private void initGroupListView() {
        QMUICommonListItemView itemWithDetail = mGroupListView.createItemView("当前账号");
        itemWithDetail.setDetailText("xpt");
        QMUICommonListItemView itemWithChevron = mGroupListView.createItemView("修改密码");
        itemWithChevron.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);



    //TODO:这里写账号与安全
        QMUIGroupListView.newSection(AccountActivity.this)
                .addItemView(itemWithDetail, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .addItemView(itemWithChevron, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AccountActivity.this,AccountPasswordActivity.class);
                        startActivity(intent);
                    }
                })
                .addTo(mGroupListView);

    }


}
