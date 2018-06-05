package com.youtome;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.youtome.app.AppApplication;
import com.youtome.tool.PrefTools;
import com.youtome.view.CommentCardView;
import com.youtome.view.superadapter.Articlefail;
import com.youtome.view.superadapter.Commentsucess;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CommentsActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mCommentFatherLayout;
    private TextView write_comment_text;
    private Handler mHandler;
    private Button read_btn;
    private Button like_btn;
    private Button forward_btn;
    Intent intent;
    private CircleImageView profile_image;
    private TextView name;
    private TextView time;
    private TextView title;
    private TextView content;
    private TextView read_count;
    private TextView like_count;
    private TextView comment_count;
    private RelativeLayout bottom_nav_bar;
    private String Username;
    private  String Token;
    private  String mname;
    private  String id;
    private String mcontent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_comments);
        StatusBarUtil.setTranslucentForCoordinatorLayout(CommentsActivity.this,50);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Username = PrefTools.getString(
                AppApplication.getContext(), "User","");
        Token = PrefTools.getString(
                AppApplication.getContext(), "Token","");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCommentFatherLayout = (LinearLayout) findViewById(R.id.comment_father_view_layout);
        mHandler = new Handler();
        write_comment_text = (TextView) findViewById(R.id.write_comment_text);
        write_comment_text.setOnClickListener(this);
        read_btn = (Button) findViewById(R.id.read_btn);
        like_btn = (Button) findViewById(R.id.like_btn);
        forward_btn=(Button)findViewById(R.id.forward_btn);
        read_btn.setOnClickListener(this);
        like_btn.setOnClickListener(this);
        forward_btn.setOnClickListener(this);
        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        name = (TextView) findViewById(R.id.name);
        time = (TextView) findViewById(R.id.time);
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        read_count = (TextView) findViewById(R.id.read_count);
        like_count = (TextView) findViewById(R.id.like_count);
        comment_count = (TextView) findViewById(R.id.comment_count);
        bottom_nav_bar = (RelativeLayout) findViewById(R.id.bottom_nav_bar);

        intent=getIntent();
        Boolean isStatus=intent.getBooleanExtra("isStatus",false);
        mcontent=intent.getStringExtra("content");
        String mtitle=intent.getStringExtra("title");
        mname=intent.getStringExtra("name");
        String mtime=intent.getStringExtra("time");
        id=intent.getStringExtra("id");
        String zf=intent.getStringExtra("zf");
        String like=intent.getStringExtra("like");
        String pl=intent.getStringExtra("pl");
        like_count.setText(like);
        read_count.setText(zf);
        comment_count.setText(pl);
        content.setText(mcontent);
        time.setText(mtime);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    RequestBody requestBody =new FormBody.Builder().add("username",Username).add("token",Token).
                            add("belong",mname).add("id",id).build();
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder().url("http://118.24.120.57:8080/education/getComment.php").post(requestBody).build();
                    final Response response= client.newCall(request).execute();
                    final String  reponseData=response.body().string();
                    Gson gson=new Gson();
                    Articlefail commentfail=gson.fromJson(reponseData,Articlefail.class);
                    if(commentfail.status.equals("fail")){;}
                    else{
                        Gson gson1=new Gson();
                    final Commentsucess commentsucess=gson1.fromJson(reponseData,Commentsucess.class);
                    final List<Commentsucess.Detail> reason=commentsucess.results;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int i=reason.size();
                                int n=0;


                                while(i>0){
                                CommentCardView cardView = new CommentCardView(CommentsActivity.this);
                                cardView.setCommentText(reason.get(n).comment);
                                cardView.setCommenter(reason.get(n).commenter);
                                cardView.setTime("     ");
                                mCommentFatherLayout.addView(cardView, 0);
                                i=i-1;
                                n=n+1;}

                            }
                        });}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        title.setVisibility(View.INVISIBLE);
        name.setText(mname);
        if(!isStatus){
            title.setVisibility(View.VISIBLE);
            title.setText(mtitle);
            forward_btn.setVisibility(View.GONE);
        }
        if(isStatus){
            read_btn.setVisibility(View.GONE);
        }

        intent.setClass(CommentsActivity.this,DetailActivity.class);




        mCommentFatherLayout.addView(new CommentCardView(CommentsActivity.this));


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




    @Override
    public void onClick(View var1) {
        switch (var1.getId()) {
            case R.id.write_comment_text:
                CommentsDialog dialog = new CommentsDialog(this, R.style.CustomDialogTheme);
                dialog.setOnDialogDismissListener(new CommentsDialog.OnDialogDismissListener() {
                    @Override
                    public void OnDismiss(final String editTextContent, boolean isPositive) {
                        if (isPositive){
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {

                                            try{
                                                RequestBody requestBody =new FormBody.Builder().add("username",Username).add("token",Token).
                                                        add("content",editTextContent).add("belong",mname).add("id",id).build();
                                                OkHttpClient client=new OkHttpClient();
                                                Request request=new Request.Builder().url("http://118.24.120.57:8080/education/commentArticle.php").post(requestBody).build();
                                                final Response response= client.newCall(request).execute();
                                                final String  reponseData=response.body().string();
                                                Gson gson=new Gson();
                                                Articlefail fail=gson.fromJson(reponseData,Articlefail.class);
                                                final String why=fail.getReason();
                                                if (fail.status.equals("fail"))
                                                {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(CommentsActivity.this, "评论失败   "+why, Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                                }
                                                else{
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(CommentsActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });}
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }).start();
                                    CommentCardView cardView = new CommentCardView(CommentsActivity.this);
                                    cardView.setCommentText(editTextContent);
                                    mCommentFatherLayout.addView(cardView, 0);//在顶端添加评论
                                }
                            });
                        }
                    }
                });
                dialog.show();
                break;
            case R.id.read_btn:
                startActivity(intent);
                break;
            case R.id.like_btn:
                if(like_btn.getText().equals("点赞")){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try{
                                RequestBody requestBody =new FormBody.Builder().add("username",Username).add("token",Token).
                                        add("belong",mname).add("id",id).add("type","3").build();
                                OkHttpClient client=new OkHttpClient();
                                Request request=new Request.Builder().url("http://118.24.120.57:8080/education/goodArticle.php").post(requestBody).build();
                                final Response response= client.newCall(request).execute();
                                final String  reponseData=response.body().string();
                                Gson gson=new Gson();
                                Articlefail fail=gson.fromJson(reponseData,Articlefail.class);
                                final String why=fail.getReason();
                                if (fail.status.equals("fail"))
                                {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(CommentsActivity.this, "失败   "+why, Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                                else{
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(CommentsActivity.this, "成功", Toast.LENGTH_SHORT).show();
                                        }
                                    });}
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    like_btn.setText("已赞");
                }else{
                    like_btn.setText("点赞");
                }
                break;
            case R.id.forward_btn:
                showForwardDialog();
                break;
        }
    }

    private void showForwardDialog() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(CommentsActivity.this);
        builder.setTitle("")
                .setPlaceholder("转发理由")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("发送", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                            //Toast.makeText(CommentsActivity.this, "已成功转发", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            //TODO:转发的逻辑
                        Intent intent=new Intent(CommentsActivity.this,SendStatusActivity.class);//跳转到这条状态的详细内容
                        intent.putExtra("reason",text.toString());
                        intent.putExtra("name",mname);
                        intent.putExtra("id",id);
                        intent.putExtra("content",mcontent);

                        startActivity(intent);
                    }
                })
                .show();
    }
}
