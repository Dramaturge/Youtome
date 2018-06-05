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
import com.youtome.app.AppApplication;
import com.youtome.tool.PrefTools;
import com.youtome.view.superadapter.Articlefail;
import com.youtome.view.superadapter.Articlesuccess;
import com.youtome.view.superadapter.Getusersucess;
import com.youtome.view.superadapter.Publish;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

/**
 *我（MainActivityUserFragment）→编辑个人资料
 * */
public class EditInformationActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Getusersucess.Detail> reason;
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
    private String Username;
    private String Token;
    private String nickname;
    private String signature;
    private String school;
    private String sex;
    private String birthday;
    private String area;
    private String year;
    private String major;
    private String area_aim;
    private String phone;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_edit_information);
        Username = PrefTools.getString(
                AppApplication.getContext(), "User","");
        Token = PrefTools.getString(
                AppApplication.getContext(), "Token","");
        StatusBarUtil.setTranslucentForCoordinatorLayout(EditInformationActivity.this,50);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    RequestBody requestBody =new FormBody.Builder().add("username",Username).add("token",Token).build();
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder().url("http://118.24.120.57:8080/education/getUserInfo.php").post(requestBody).build();
                    final Response response= client.newCall(request).execute();
                    final String  reponseData=response.body().string();
                    Gson gson=new Gson();
                    Getusersucess getusersucess=gson.fromJson(reponseData,Getusersucess.class);
                    reason=getusersucess.results;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            met_nick_name.setText(reason.get(0).nickname);
                            met_signature.setText(reason.get(0).signature);
                            met_school.setText(reason.get(0).school);
                            met_gender.setText(reason.get(0).sex);
                            met_birth_date.setText(reason.get(0).birthday);
                            met_region.setText(reason.get(0).area);
                            met_phone.setText(reason.get(0).phone);
                            met_entrance_date.setText(reason.get(0).year);
                            met_intention_region.setText(reason.get(0).area_aim);
                            met_intention_major.setText(reason.get(0).major);
                            met_email.setText(reason.get(0).email);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
                nickname=met_nick_name.getText().toString();
                sex=met_gender.getText().toString();
                school=met_school.getText().toString();
                signature=met_signature.getText().toString();
                PrefTools.setString(EditInformationActivity.this,"personal_signal",signature);
                year=met_entrance_date.getText().toString();
                birthday=met_birth_date.getText().toString();
                email=met_email.getText().toString();
                phone=met_phone.getText().toString();
                area=met_region.getText().toString();
                area_aim=met_intention_region.getText().toString();
                major=met_intention_major.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            RequestBody requestBody =new FormBody.Builder().add("username",Username).add("token",Token).
                                    add("nickname",nickname).add("sex",sex).add("school",school).add("signature",signature).add("year",year).add("area",area).add("area_aim",area_aim).add("birthday",birthday).add("major",major).add("phone",phone).add("email",email).build();
                            OkHttpClient client=new OkHttpClient();
                            Request request=new Request.Builder().url("http://118.24.120.57:8080/education/perfect_info.php").post(requestBody).build();
                            final Response response= client.newCall(request).execute();
                            final String  reponseData=response.body().string();
                            Gson gson=new Gson();
                            Articlefail fail=gson.fromJson(reponseData,Articlefail.class);
                            final String why=fail.getReason();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(EditInformationActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                finish();

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
