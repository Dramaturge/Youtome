package com.youtome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StatusBarUtil.setColor(RegisterActivity.this, getResources().getColor(R.color.shallowGray), 50);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        final EditText email=(EditText) findViewById(R.id.register_email);
        final EditText password=(EditText)  findViewById(R.id.register_password);
        final CheckBox agree=(CheckBox)  findViewById(R.id.register_agree);
        final TextView note=(TextView) findViewById(R.id.register_note);
        Button button=(Button) findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agree.isChecked()==true){
                    String Email =email.getText().toString();
                    String Password=password.getText().toString();
                    note.setText("");
                    Intent intent= new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                }else
                {
                    note.setText("未同意用户协议");
                }
            }
        });

    }
}
