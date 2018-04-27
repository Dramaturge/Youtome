package com.youtome;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Objects;

/**
 * 发送评论的会话框
 */

public class CommentsDialog extends Dialog implements View.OnClickListener{
    private TextView mTitle;
    private TextView mPostiveBtn;
    private TextView mNegativeBtn;
    private EditText mEditText;
    private OnDialogDismissListener mListener;

    private String mPostiveName;
    private String mNegativeName;
    private String mTitleName;

    public CommentsDialog(@NonNull Context context) {
        super(context);
    }

    public CommentsDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected CommentsDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comments);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.comment_content);
        mTitle = (TextView) findViewById(R.id.dialog_title);
        mPostiveBtn = (TextView) findViewById(R.id.positive_btn);
        mNegativeBtn = (TextView) findViewById(R.id.negative_btn);
        mPostiveBtn.setOnClickListener(this);
        mNegativeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.positive_btn:
                if (mListener != null && !Objects.equals(mEditText.getText().toString(), "")){
                    mListener.OnDismiss(mEditText.getText().toString(), true);
                    dismiss();
                }else {
                    Toast.makeText(getContext(), "请不要发送空评论", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.negative_btn:
                if (mListener != null){
                    mListener.OnDismiss(mEditText.getText().toString(), false);
                }
                dismiss();
                break;
        }
    }
    public CommentsDialog setMyTitle(String titleName){
        mTitle.setText(titleName);
        return this;
    }
    public CommentsDialog setPositiveName(String positiveName){
        mPostiveBtn.setText(positiveName);
        return this;
    }
    public CommentsDialog setNegativeName(String negativeName){
        mNegativeBtn.setText(negativeName);
        return this;
    }
    public CommentsDialog setOnPostiveButtonClicked(View.OnClickListener listener){
        mPostiveBtn.setOnClickListener(listener);
        return this;
    }
    public CommentsDialog setOnNegativeButtonClicked(View.OnClickListener listener){
        mNegativeBtn.setOnClickListener(listener);
        return this;
    }
    public CommentsDialog setOnDialogDismissListener(OnDialogDismissListener listener){
        mListener = listener;
        return this;
    }

    public interface OnDialogDismissListener{
        void OnDismiss(String editTextContent, boolean isPositive);
    }
}
