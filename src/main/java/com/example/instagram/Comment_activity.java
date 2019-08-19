package com.example.instagram;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class Comment_activity extends AppCompatActivity {
    EditText addcomment;
    ImageView img_profile;
    TextView post;
    String PostId,UserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_activity);

        Toolbar toolbar=findViewById(R.id.toolbar_comment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Comments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addcomment=findViewById(R.id.tv_comment);
        img_profile=findViewById(R.id.img_profile_comment);
        post=findViewById(R.id.tv_post);

        Intent intent=getIntent();
        PostId=intent.getStringExtra("objectId");
        UserId=intent.getStringExtra("user");

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addcomment.getText().equals("")){
                    Toast.makeText(Comment_activity.this, "You can't send empty comment", Toast.LENGTH_SHORT).show();
                }else {
                    String comment=addcomment.getText().toString();
                    ParseUser user= ParseUser.getCurrentUser();
                    addThecomment(comment,user);
                    finish();
                }
            }
        });
    }

    private void addThecomment(final String comment, ParseUser parseUser) {
        Post post=new Post();
        post.setComment(comment);
        post.setUser(parseUser);
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    Log.d("EROOR","Error while posting the picture");
                    e.printStackTrace();
                    return;
                }
                Log.d("EROOR","Success");

            }
        });
    }
}
