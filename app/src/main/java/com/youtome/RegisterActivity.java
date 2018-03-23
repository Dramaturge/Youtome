package com.youtome;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.youtome.view.superadapter.Signup;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView textView =(TextView) findViewById(R.id.register_youtome);
        textView.setTypeface(Typeface.SERIF);
        final CheckBox agree=(CheckBox)  findViewById(R.id.register_assign);
        Button button=(Button) findViewById(R.id.register_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText username=(EditText) findViewById(R.id.register_username);
                final EditText password=(EditText)  findViewById(R.id.register_password);
                final EditText note=(EditText) findViewById(R.id.register_againpass);
                final String Username=username.getText().toString();
                final String Password=password.getText().toString();
                String Passagain=note.getText().toString();
                if(Password.equals(Passagain)){
                    if(agree.isChecked()==true){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                RequestBody requestBody =new FormBody.Builder().add("username",Username).add("password",Password).build();
                                OkHttpClient client=new OkHttpClient();
                                Request request=new Request.Builder().url("http://118.24.120.57:8080/education/signup.php").post(requestBody).build();
                                final Response response= client.newCall(request).execute();
                                final String  reponseData=response.body().string();
                                Gson gson=new Gson();
                                Signup user=gson.fromJson(reponseData,Signup.class);
                                final String status=user.getStatus();
                                final String reason=user.getReason();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //UI
                                        if(status.equals("success")){
                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                        }else{Toast toast=Toast.makeText(getApplicationContext(), "注册失败"+"   "+reason, Toast.LENGTH_SHORT);
                                        toast.show();}
                                    }
                                });


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();}
                    else{
                    Toast toast=Toast.makeText(getApplicationContext(), "未同意用户协议", Toast.LENGTH_SHORT);
                    toast.show();}
                }else{
                    Toast toast=Toast.makeText(getApplicationContext(), "两次输入密码不一致", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        Button yhxy=(Button) findViewById(R.id.register_yhxy);
        yhxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, UserxyActivity.class);
                startActivity(intent);
            }
        });
    }
}
