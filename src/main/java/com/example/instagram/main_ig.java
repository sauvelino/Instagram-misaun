package com.example.instagram;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.instagram.ui.main.SectionsPagerAdapter;

public class main_ig extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ig);

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
    }
}