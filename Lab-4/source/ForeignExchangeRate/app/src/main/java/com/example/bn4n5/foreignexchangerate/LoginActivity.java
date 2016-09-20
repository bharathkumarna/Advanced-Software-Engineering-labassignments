package com.example.bn4n5.foreignexchangerate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
EditText username,password;

     Button signin;
     TextView message;
    String user,psswd;
    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pwd);
        signin = (Button) findViewById(R.id.btn_login);
        message = (TextView) findViewById(R.id.msg);

        signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                username = (EditText)findViewById(R.id.username);
                password = (EditText) findViewById(R.id.pwd);
                user = username.getText().toString();
                psswd = password.getText().toString();

                if (user.equals("admin") && psswd.equals("admin")) {
                    message.setText("Login Successful!");
                    Intent redirect = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(redirect);
                } else {
                    message.setText("Login Unsuccessful!");
                }
            }
        });
    }
    }

