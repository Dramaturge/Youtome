package com.youtome;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.youtome.app.AppApplication;
import com.youtome.bean.Status;
import com.youtome.tool.PrefTools;
import com.youtome.view.RecyclerViewCommonTool.CommonAdapter;
import com.youtome.view.RecyclerViewCommonTool.ViewHolder;
import com.youtome.view.superadapter.Adduser;
import com.youtome.view.superadapter.LayoutWrapper;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 *他人主页
 * 通过点击他人头像进入
 * */
public class OtherHomepageActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayoutState state;
    private  CommonAdapter adapter;
    private List<LayoutWrapper> data;
    private SwipeRefreshLayout lay_fresh;
    private  ArrayList<Status> statusArrayList;


    private ImageView collapsing_bar_background_image;
    private Toolbar user_tool_bar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout app_bar;
    private CircleImageView profile_image;
    private TextView user_name;
    private TextView personal_signal;
    private TextView personal_brief;
    private TextView focus_count;
    private TextView fans_count;
    private TextView read_count_text;
    private TextView likes_count;
    private LinearLayout various_counts_layout;
    private RecyclerView status_recycler;
    private LinearLayout user_information_all;
    private NestedScrollView scroll_view;
    private String Username;
    private String Token;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_other_homepage);
        StatusBarUtil.setTranslucentForCoordinatorLayout(OtherHomepageActivity.this,50);
        Username = PrefTools.getString(
                AppApplication.getContext(), "User","");
        Token = PrefTools.getString(
                AppApplication.getContext(), "Token","");
        Intent intent=getIntent();
        String publisher=intent.getStringExtra("publisher");
        user_name=(TextView) findViewById(R.id.user_name);
        user_name.setText(publisher);
        initView();

    }

    private void initView() {
        collapsing_bar_background_image = (ImageView) findViewById(R.id.collapsing_bar_background_image);
        user_tool_bar = (Toolbar) findViewById(R.id.user_tool_bar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.tool_bar_layout);
        app_bar = (AppBarLayout) findViewById(R.id.app_bar);
        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        user_name = (TextView) findViewById(R.id.user_name);
        personal_signal = (TextView) findViewById(R.id.personal_signal);
        personal_brief = (TextView) findViewById(R.id.personal_brief);
        focus_count = (TextView) findViewById(R.id.focus_count);
        fans_count = (TextView) findViewById(R.id.fans_count);
        read_count_text = (TextView) findViewById(R.id.read_count_text);
        likes_count = (TextView) findViewById(R.id.likes_count);
        various_counts_layout = (LinearLayout) findViewById(R.id.various_counts_layout);
        user_information_all = (LinearLayout) findViewById(R.id.user_information_all);
        scroll_view = (NestedScrollView) findViewById(R.id.scroll_view);
        setSupportActionBar(user_tool_bar);
        getSupportActionBar().setTitle("");


        statusArrayList=new ArrayList<Status>();
        statusArrayList.add(new Status(
                R.drawable.header2,
                "刘一林(老师)",
                "4-13",
                "    晚上十点了，自习室里还是坐满了人，西电的学风就是好！",
                "10",
                "2",
                "5",
        "4"));
        statusArrayList.add(new Status(
                R.drawable.header2,
                "刘一林(老师)",
                "3-31",
                "    谢谢大家的配合，如果大家对我讲的课有什么意见和建议，欢迎给我留言。",
                "100",
                "0",
                "30",
                "5"));
        statusArrayList.add(new Status(
                R.drawable.header2,
                "刘一林(老师)",
                "3-30",
                "    明天晚上九点我计划给大家讲一节圆锥曲线专题，大家可以小小的期待一下哦！",
                "50",
                "25",
                "20",
                "4"));
        statusArrayList.add(new Status(
                R.drawable.header2,
                "刘一林(老师)",
                "3-20",
                "    大家好，我是来自西安电子科技大学的刘一林。请大家多多关照。如果大家在数学学习上遇到什么困难，可以来问我。",
                "50",
                "0",
                "20",
                "5"));

        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        profile_image.setVisibility(View.VISIBLE);
                        collapsingToolbarLayout.setTitle("");
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                        profile_image.setVisibility(View.INVISIBLE);
                        getSupportActionBar().setTitle("刘一林的主页");
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if(state == CollapsingToolbarLayoutState.COLLAPSED){
                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                        profile_image.setVisibility(View.VISIBLE);
                        collapsingToolbarLayout.setTitle("");

                    }
                }
            }
        });
        
        
        mRecyclerView = (RecyclerView) findViewById(R.id.status_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration mDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider_bg));
        mRecyclerView.addItemDecoration(mDecoration);


        adapter=new CommonAdapter<Status>(OtherHomepageActivity.this, R.layout.item_status, statusArrayList){
            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position) {
                super.onBindViewHolder(viewHolder, position);
            }

            @Override
            public void convert(ViewHolder holder, final Status data) {
                LinearLayout skip_to_others_homepage=holder.getView(R.id.skip_to_others_homepage);
                CircleImageView profile_image = holder.getView(R.id.profile_image);//头像
                TextView tv_status_publisher =holder.getView(R.id.tv_status_publisher);//发布人
                TextView tv_status_publish_time =holder.getView(R.id.tv_status_publish_time);//发布时间
                LinearLayout skip_to_detail=holder.getView(R.id.skip_to_detail);
                TextView tv_status_content =holder.getView(R.id.tv_status_content);//内容
                TextView tv_like_number =holder.getView(R.id.tv_like_number);//赞同数
                TextView tv_comments_number =holder.getView(R.id.tv_comments_number);//评论数

                profile_image.setImageDrawable(getResources().getDrawable(data.getHeader()));
                tv_status_publisher.setText(data.getPublisher());
                tv_status_publish_time.setText(data.getPublish_time());
                tv_like_number.setText(data.getLike_number());
                tv_status_content.setText(data.getContent());
                tv_comments_number.setText(data.getComments_number());



                skip_to_others_homepage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(OtherHomepageActivity.this,OtherHomepageActivity.class);//跳转到发布人的个人主页
                        startActivity(intent);
                    }
                });


                skip_to_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(OtherHomepageActivity.this,CommentsActivity.class);//跳转到这条状态的详细内容
                        intent.putExtra("content",data.getContent());
                        intent.putExtra("name",data.getPublisher());
                        intent.putExtra("time",data.getPublish_time());
                        intent.putExtra("isStatus",true);
                        startActivity(intent);
                    }
                });


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
                showMessagePositiveDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMessagePositiveDialog() {
        new QMUIDialog.MessageDialogBuilder(OtherHomepageActivity.this)
                .setTitle("关注对方")
                .setMessage("关注后你可以在朋友圈中看到他的动态")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        user_name=(TextView) findViewById(R.id.user_name);
                        final String Add=user_name.getText().toString();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    RequestBody requestBody =new FormBody.Builder().add("username",Username).add("token",Token).add("friend",Add).build();
                                    OkHttpClient client=new OkHttpClient();
                                    Request request=new Request.Builder().url("http://118.24.120.57:8080/education/addFriend.php").post(requestBody).build();
                                    final Response response= client.newCall(request).execute();
                                    final String  reponseData=response.body().string();
                                    Gson gson=new Gson();
                                    Adduser adduser=gson.fromJson(reponseData,Adduser.class);
                                    final String status=adduser.getStatus();
//                                    if(status.equals("fail")){
//
//                                        final String reason=adduser.getReason();
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//
//                                                Toast.makeText(OtherHomepageActivity.this, "关注失败 "+reason, Toast.LENGTH_SHORT).show();
//                                            }
//                                        });
//                                    }
//                                    else{
//
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                Toast.makeText(OtherHomepageActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
//                                            }
//
//                                        });
//
//                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        Toast.makeText(OtherHomepageActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
