package com.youtome;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.youtome.view.CommentCardView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentsActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mCommentFatherLayout;
    private TextView write_comment_text;
    private Handler mHandler;
    private Button read_btn;
    private Button like_btn;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_comments);
        StatusBarUtil.setTranslucentForCoordinatorLayout(CommentsActivity.this,50);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCommentFatherLayout = (LinearLayout) findViewById(R.id.comment_father_view_layout);
        mHandler = new Handler();
        write_comment_text = (TextView) findViewById(R.id.write_comment_text);
        write_comment_text.setOnClickListener(this);
        read_btn = (Button) findViewById(R.id.read_btn);
        like_btn = (Button) findViewById(R.id.like_btn);
        read_btn.setOnClickListener(this);
        like_btn.setOnClickListener(this);
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
        String mcontent=intent.getStringExtra("content");
        String mtitle=intent.getStringExtra("title");
        String mname=intent.getStringExtra("name");
        String mtime=intent.getStringExtra("time");
        content.setText(mcontent);
        time.setText(mtime);
        title.setVisibility(View.INVISIBLE);
        name.setText(mname);
        if(!isStatus){
            title.setVisibility(View.VISIBLE);
            title.setText(mtitle);
        }
        if(isStatus){
            read_btn.setVisibility(View.GONE);
        }

        intent.setClass(CommentsActivity.this,DetailActivity.class);



        mCommentFatherLayout.addView(new CommentCardView(CommentsActivity.this));
        mCommentFatherLayout.addView(new CommentCardView(CommentsActivity.this));
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
                    like_btn.setText("已赞");
                }else{
                    like_btn.setText("点赞");
                }
                break;
        }
    }


}
