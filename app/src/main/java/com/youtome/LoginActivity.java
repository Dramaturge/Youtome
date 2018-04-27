package com.youtome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.youtome.app.AppApplication;
import com.youtome.tool.PrefTools;
import com.youtome.view.superadapter.Signin;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private String Username;
    private  String Token;
    private SharedPreferences pref;
    private CheckBox mRemember;
    private String Password;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        TextView textView =(TextView) findViewById(R.id.login_youtome);
        textView.setTypeface(Typeface.SERIF);
        final EditText password=(EditText) findViewById(R.id.login_password) ;
        final EditText username=(EditText) findViewById(R.id.login_username) ;
        Button login=(Button) findViewById(R.id.login_button);
        Button register=(Button) findViewById(R.id.login_register);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        mRemember = (CheckBox) findViewById(R.id.login_remember);
        boolean isRemember = pref.getBoolean("remember_pass", false);
        if (isRemember) {
            String user = pref.getString("username", "");
            String pass = pref.getString("password", "");
            username.setText(user);
            password.setText(pass);
            mRemember.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor = pref.edit();
                if (mRemember.isChecked()) {
                    editor.putBoolean("remember_pass", true);
                    editor.putString("username", username.getText().toString());
                    editor.putString("password", password.getText().toString());
                } else {
                    editor.clear();
                }
                editor.apply();
                Username=username.getText().toString();
                Password=password.getText().toString();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {

                            RequestBody requestBody = new FormBody.Builder().add("username", Username).add("password", Password).build();
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder().url("http://118.24.120.57:8080/education/signin.php").post(requestBody).build();
                            final Response response = client.newCall(request).execute();
                            final String reponseData = response.body().string();
                            Gson gson = new Gson();
                            Signin user = gson.fromJson(reponseData, Signin.class);
                            final String status = user.getStatus();
                            final String reason = user.getReason();
                            Token = user.getToken();
                            PrefTools.setString(AppApplication.getContext(),"User",Username);
                            PrefTools.setString(AppApplication.getContext(),"Token",Token);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //UI
                                    if (status.equals("success")) {

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast toast=Toast.makeText(getApplicationContext(), "登录失败，请检查用户名、密码", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }

                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

    }

    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this,"再按一次退出",
                        Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        //拦截MENU按钮事件，让他无任何操作
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
