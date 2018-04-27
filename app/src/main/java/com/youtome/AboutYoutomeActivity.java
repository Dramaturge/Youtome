package com.youtome;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;

/**
 *我（MainActivityUserFragment）→关于Youtome
 * */
public class AboutYoutomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private TextView tv_company_brief;
    private TextView tv_check_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_about_youtome);
        StatusBarUtil.setTranslucentForCoordinatorLayout(AboutYoutomeActivity.this,50);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    private void initView() {
        tv_company_brief = (TextView) findViewById(R.id.tv_company_brief);
        tv_check_new = (TextView) findViewById(R.id.tv_check_new);
        tv_company_brief.setOnClickListener(this);
        tv_check_new.setOnClickListener(this);
    }

    @Override
    public void onClick(View var1) {
        switch (var1.getId()) {
            case R.id.tv_company_brief:
                Intent intent=new Intent(AboutYoutomeActivity.this,DetailActivity.class);//跳转到这条状态的详细内容
                intent.putExtra("content",
                        "        彼端芒果公益教育旨在解决教育资源分布不均匀，高中生欠缺学习方法，报考信息来源闭塞等问题。采取芒果实体教育与youtome学生服务平台相结合，充分发挥“互联网+教育”优势，采取“共享经济模式”，撬动优秀大学生群体，为高中生提供包括交流对接，报考咨询，家教辅导等公益服务，同时也为大学生提供安全可靠的兼职岗位。\n" +
                        "        经过三年实践探索，我们已建立一个山东校区，两个西安校区，累计帮助高中生2531名，激励高中生数万名。未来我们希望走向全国，怀揣公益之心，付之公益之行\n");
                intent.putExtra("title","彼端公司芒果公益教育");
                startActivity(intent);
                break;
            case R.id.tv_check_new:
                Toast.makeText(this,"已是最新版本",
                        Toast.LENGTH_SHORT).show();
                break;
        }
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
