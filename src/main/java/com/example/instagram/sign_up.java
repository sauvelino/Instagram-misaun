package com.example.instagram;

import android.app.Application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText Username,Email,Password,Password_confirm;
        Button btn_sing_in;


        Username=findViewById(R.id.edt_username);
        Email=findViewById(R.id.edt_email);
        Password=findViewById(R.id.edt_password);
        Password_confirm=findViewById(R.id.edt_password_confirm);
        btn_sing_in=findViewById(R.id.btn_sign_in);

        btn_sing_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=Username.getText().toString();
                String email=Email.getText().toString();
                String pass=Password.getText().toString();
                String pass_confirm=Password_confirm.getText().toString();


                if(user.isEmpty()||email.isEmpty()||pass.isEmpty()||pass_confirm.isEmpty()){

                    Toast.makeText(sign_up.this, "empty", Toast.LENGTH_SHORT).show();

                    return;

                }
                if(!pass.contentEquals(pass_confirm)){
                    Toast.makeText(sign_up.this, "password not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                sign_up_Parse(user,email,pass);

            }
        });
    }

    private void sign_up_Parse(String user, String email, String pass) {
        ParseUser user1=new ParseUser();
        user1.setUsername(user);
        user1.setEmail(email);
        user1.setPassword(pass);
        user1.put("username",user);
        user1.put("password",pass);
        user1.put("email",email);
        user1.put("handle",user);

        user1.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    startActivity(new Intent(sign_up.this,MainActivity.class));
                    Toast.makeText(sign_up.this, "good", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    e.printStackTrace();
                    Toast.makeText(sign_up.this, "Error "+e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
    ParseUser.logOut();
    ParseUser currentUser=ParseUser.getCurrentUser();
    startActivity(new Intent(this,MainActivity.class));
    finish(); */
}
