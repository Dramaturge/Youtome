package com.youtome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.youtome.app.AppApplication;
import com.youtome.tool.PrefTools;
import com.youtome.view.superadapter.Articlefail;
import com.youtome.view.superadapter.Getusersucess;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

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
    private RelativeLayout to_friends;
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
    private  String img;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View FindFragment = inflater.inflate(R.layout.act_main_frg_user, container, false);
        Username = PrefTools.getString(
                AppApplication.getContext(), "User","");
        Token = PrefTools.getString(
                AppApplication.getContext(), "Token","");
        //String personal_brief=PrefTools.getString(getContext(),"personal_signal","点击此处编辑个性签名");


        app_bar=(AppBarLayout)FindFragment.findViewById(R.id.app_bar);
        this.to_account = (RelativeLayout) FindFragment.findViewById(R.id.to_account);
        to_account.setOnClickListener(this);
        this.to_personal_information = (RelativeLayout) FindFragment.findViewById(R.id.to_personal_information);
        to_personal_information.setOnClickListener(this);
        this.to_about = (RelativeLayout) FindFragment.findViewById(R.id.to_about);
        to_about.setOnClickListener(this);
        this.to_friends = (RelativeLayout) FindFragment.findViewById(R.id.to_friends);
        to_friends.setOnClickListener(this);
        this.fans_count = (TextView) FindFragment.findViewById(R.id.fans_count);
        this.read_count = (TextView) FindFragment.findViewById(R.id.read_count);
        this.recommend_count = (TextView) FindFragment.findViewById(R.id.recommend_count);
        this.edit_personal_brief = (TextView) FindFragment.findViewById(R.id.edit_personal_brief);

        this.edit_personal_signal = (TextView) FindFragment.findViewById(R.id.edit_personal_signal);
        edit_personal_signal.setOnClickListener(this);
        //edit_personal_signal.setText(personal_brief);

        this.user_name = (TextView) FindFragment.findViewById(R.id.user_name);
        this.profile_image = (CircleImageView) FindFragment.findViewById(R.id.profile_image);
        this.collapsingbar_background_image = (ImageView) FindFragment.findViewById(R.id.collapsing_bar_background_image);

        profile_image.setImageDrawable(getResources().getDrawable(R.drawable.header1));
        this.quit=(QMUIRoundButton)FindFragment.findViewById(R.id.quit);
        quit.setOnClickListener(this);
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
                    final List<Getusersucess.Detail> reason = getusersucess.results;
                    PrefTools.setString(getActivity(),"nickname",reason.get(0).nickname);
                    PrefTools.setString(getActivity(),"signature",reason.get(0).signature);
                    PrefTools.setString(getActivity(),"sex",reason.get(0).sex);
                    PrefTools.setString(getActivity(),"school",reason.get(0).school);
                    PrefTools.setString(getActivity(),"year",reason.get(0).year);
                    PrefTools.setString(getActivity(),"area",reason.get(0).area);
                    PrefTools.setString(getActivity(),"area_aim",reason.get(0).area_aim);
                    PrefTools.setString(getActivity(),"birthday",reason.get(0).birthday);
                    PrefTools.setString(getActivity(),"phone",reason.get(0).phone);
                    PrefTools.setString(getActivity(),"email",reason.get(0).email);
                    PrefTools.setString(getActivity(),"major",reason.get(0).major);
                    PrefTools.setString(getActivity(),"img",reason.get(0).img);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            edit_personal_signal.setText(reason.get(0).signature);
                            edit_personal_brief.setText(reason.get(0).school+"  "+reason.get(0).area);
                            nickname=reason.get(0).nickname;

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        nickname=PrefTools.getString(getContext(),"nickname","sb");
        user_name.setText(nickname);
        img=PrefTools.getString(getContext(),"img","sb");
        area=PrefTools.getString(getContext(),"area","sb");
        area_aim=PrefTools.getString(getContext(),"area_aim","sb");
        school=PrefTools.getString(getContext(),"school","sb");
        sex=PrefTools.getString(getContext(),"sex","sb");
        signature=PrefTools.getString(getContext(),"signature","sb");
        birthday=PrefTools.getString(getContext(),"birthday","sb");
        year=PrefTools.getString(getContext(),"year","sb");
        major=PrefTools.getString(getContext(),"major","sb");
        phone=PrefTools.getString(getContext(),"phone","sb");
        email=PrefTools.getString(getContext(),"email","sb");
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
            case R.id.to_friends:
                Intent intent4=new Intent(getContext(),FriendsActivity.class);
                startActivity(intent4);
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
                        signature=text.toString();
                        if (text != null && text.length() > 0) {
                            edit_personal_signal.setText(text);
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
                                                Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                            //PrefTools.setString(getActivity(),"personal_signal",text.toString());
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
