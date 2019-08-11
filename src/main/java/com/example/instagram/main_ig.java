package com.example.instagram;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.instagram.ui.main.PlaceholderFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.instagram.ui.main.SectionsPagerAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class main_ig extends AppCompatActivity {
ImageView btn_camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ig);

        LoadFragment(new PlaceholderFragment());


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater layoutInflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View action_bar_view=layoutInflater.inflate(R.layout.custom_toolbar_home,null);
        actionBar.setCustomView(action_bar_view);


        //for custom actionbar
tabs.getTabAt(0).setIcon(R.drawable.instagram_home_outline_24);
tabs.getTabAt(1).setIcon(R.drawable.ic_vector_search_stroke);
tabs.getTabAt(2).setIcon(R.drawable.instagram_new_post_outline_24);
tabs.getTabAt(3).setIcon(R.drawable.ufi_heart);
tabs.getTabAt(4).setIcon(R.drawable.instagram_user_outline_24);


btn_camera=findViewById(R.id.camera);
//Querypost();
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(main_ig.this,Take.class));

            }
        });
    }

    public  Boolean LoadFragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.view_pager,fragment).commit();
            return true;
        }else {return false;}
    }

    private void Querypost() {
        ParseQuery<Post> postParseQuery=new ParseQuery<Post>(Post.class);
        postParseQuery.include(Post.Key_user);
        postParseQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if(e!=null){
                    Log.d("TAG","ERROR QUERY");
                    e.printStackTrace();
                    return;
                }
                for(int i=0;i<objects.size();i++){
                    Log.d("TAG","Post: "+objects.get(i).getDescription() +" Username :" +objects.get(i).getUser().getUsername());
                }
            }
        });
    }
}