package com.youtome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.youtome.app.AppApplication;
import com.youtome.tool.PrefTools;


import de.hdodenhof.circleimageview.CircleImageView;

/***
 *我Fragment
 */
public class MainActivityUserFragment extends Fragment implements View.OnClickListener {

    private AppBarLayout app_bar;
    private android.widget.ImageView collapsingbar_background_image;
    private de.hdodenhof.circleimageview.CircleImageView profile_image;
    private android.widget.TextView user_name;
    private android.widget.TextView edit_personal_signal;
    private android.widget.TextView edit_personal_brief;
    private android.widget.TextView recommend_count;
    private android.widget.TextView read_count;
    private android.widget.TextView fans_count;
    private RelativeLayout to_about;
    private RelativeLayout to_personal_information;
    private RelativeLayout to_account;
    private QMUIRoundButton quit;
    private String Username;
    private String Token;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View FindFragment = inflater.inflate(R.layout.act_main_frg_user, container, false);
        Username = PrefTools.getString(
                AppApplication.getContext(), "User","");
        Token = PrefTools.getString(
                AppApplication.getContext(), "Token","");
        String personal_brief=PrefTools.getString(getContext(),"personal_signal","点击此处编辑个性签名");


        app_bar=(AppBarLayout)FindFragment.findViewById(R.id.app_bar);
        this.to_account = (RelativeLayout) FindFragment.findViewById(R.id.to_account);
        to_account.setOnClickListener(this);
        this.to_personal_information = (RelativeLayout) FindFragment.findViewById(R.id.to_personal_information);
        to_personal_information.setOnClickListener(this);
        this.to_about = (RelativeLayout) FindFragment.findViewById(R.id.to_about);
        to_about.setOnClickListener(this);
        this.fans_count = (TextView) FindFragment.findViewById(R.id.fans_count);
        this.read_count = (TextView) FindFragment.findViewById(R.id.read_count);
        this.recommend_count = (TextView) FindFragment.findViewById(R.id.recommend_count);
        this.edit_personal_brief = (TextView) FindFragment.findViewById(R.id.edit_personal_brief);

        this.edit_personal_signal = (TextView) FindFragment.findViewById(R.id.edit_personal_signal);
        edit_personal_signal.setOnClickListener(this);
        edit_personal_signal.setText(personal_brief);

        this.user_name = (TextView) FindFragment.findViewById(R.id.user_name);
        this.profile_image = (CircleImageView) FindFragment.findViewById(R.id.profile_image);
        this.collapsingbar_background_image = (ImageView) FindFragment.findViewById(R.id.collapsing_bar_background_image);
        user_name.setText("徐鹏涛");
        profile_image.setImageDrawable(getResources().getDrawable(R.drawable.header1));
        this.quit=(QMUIRoundButton)FindFragment.findViewById(R.id.quit);
        quit.setOnClickListener(this);
        return FindFragment;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_personal_signal:
                showEditSignalDialog();
                break;
            case R.id.quit:
                showQuitDialog();
                break;
            case R.id.to_account:
                Intent intent3=new Intent(getContext(),AccountActivity.class);
                startActivity(intent3);
                break;
            case R.id.to_about:
                Intent intent=new Intent(getContext(),AboutYoutomeActivity.class);
                startActivity(intent);
                break;
            case R.id.to_personal_information:
                Intent intent2=new Intent(getContext(),EditInformationActivity.class);
                startActivity(intent2);
                break;

        }

    }

    private void showEditSignalDialog() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getActivity());
        builder.setTitle("编辑个性签名")
                .setPlaceholder("在此输入您的个性签名")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            edit_personal_signal.setText(text);
                            PrefTools.setString(getActivity(),"personal_signal",text.toString());
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getActivity(), "请填入您的个性签名", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .show();
    }



    private void showQuitDialog() {
        new QMUIDialog.CheckBoxMessageDialogBuilder(getActivity())
                .setTitle("退出后是否删除账号信息?")
                .setMessage("删除账号信息").setChecked(true)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("退出", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        PrefTools.setBoolean(AppApplication.getContext(), "is_login", false);
                        getActivity().finish();
                        Intent intent=new Intent(getContext(),LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }
}
