package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
TextView User,Pass,sign_in_text_view;
Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User=findViewById(R.id.Username);
        Pass=findViewById(R.id.Password);
        btn_login=findViewById(R.id.Btn_login);
        sign_in_text_view=findViewById(R.id.sign_in_text_view);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String U=User.getText().toString();
                String P=Pass.getText().toString();
                Login(U,P);
            }
        });


        sign_in_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,sign_up.class));
                finish();
            }
        });
       /* Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,main_ig.class));
            }
        },2000); */
    }

    private void Login(String Username,String Password) {
        ParseUser.logInInBackground(Username, Password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!=null){
                    Log.d("Erreur","Something bad is happening");
                    e.printStackTrace();
                    return;
                }
                startActivity(new Intent(MainActivity.this,main_ig.class));
                finish();
            }
        });

    }
}
