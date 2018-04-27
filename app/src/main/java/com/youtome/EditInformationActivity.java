package com.youtome;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.youtome.view.superadapter.Publish;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 *我（MainActivityUserFragment）→编辑个人资料
 * */
public class EditInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialEditText met_nick_name;
    private MaterialEditText met_signature;
    private MaterialEditText met_school;
    private MaterialEditText met_gender;
    private MaterialEditText met_birth_date;
    private MaterialEditText met_region;
    private MaterialEditText met_entrance_date;
    private MaterialEditText met_intention_major;
    private MaterialEditText met_intention_region;
    private MaterialEditText met_phone;
    private MaterialEditText met_email;
    private Button privacy_xy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_edit_information);
        StatusBarUtil.setTranslucentForCoordinatorLayout(EditInformationActivity.this,50);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
    }

    //生成菜单
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tick_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.tick_icon:
                finish();
                Toast.makeText(EditInformationActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        met_nick_name = (MaterialEditText) findViewById(R.id.met_nick_name);
        met_signature = (MaterialEditText) findViewById(R.id.met_signature);
        met_school = (MaterialEditText) findViewById(R.id.met_school);
        met_gender = (MaterialEditText) findViewById(R.id.met_gender);
        met_birth_date = (MaterialEditText) findViewById(R.id.met_birth_date);
        met_region = (MaterialEditText) findViewById(R.id.met_region);
        met_entrance_date = (MaterialEditText) findViewById(R.id.met_entrance_date);
        met_intention_major = (MaterialEditText) findViewById(R.id.met_intention_major);
        met_intention_region = (MaterialEditText) findViewById(R.id.met_intention_region);
        met_phone = (MaterialEditText) findViewById(R.id.met_phone);
        met_email = (MaterialEditText) findViewById(R.id.met_email);
        privacy_xy = (Button) findViewById(R.id.privacy_xy);
        privacy_xy.setOnClickListener(this);
    }

    @Override
    public void onClick(View var1) {
        switch (var1.getId()) {
            case R.id.privacy_xy:
                Intent intent=new Intent(EditInformationActivity.this,DetailActivity.class);//跳转到这条状态的详细内容
                intent.putExtra("content",
                        "        彼端芒果公益教育旨在解决教育资源分布不均匀，高中生欠缺学习方法，报考信息来源闭塞等问题。采取芒果实体教育与youtome学生服务平台相结合，充分发挥“互联网+教育”优势，采取“共享经济模式”，撬动优秀大学生群体，为高中生提供包括交流对接，报考咨询，家教辅导等公益服务，同时也为大学生提供安全可靠的兼职岗位。\n" +
                                "        经过三年实践探索，我们已建立一个山东校区，两个西安校区，累计帮助高中生2531名，激励高中生数万名。未来我们希望走向全国，怀揣公益之心，付之公益之行\n");
                intent.putExtra("title","隐私协议");
                startActivity(intent);
                break;
        }
    }
}
