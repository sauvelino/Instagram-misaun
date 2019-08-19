package com.example.instagram;
import android.content.Intent;
import android.os.Bundle;
import com.example.instagram.ui.main.PlaceholderFragment;
import com.example.instagram.ui.main.likes_figment_fragment;
import com.example.instagram.ui.main.profil_fragment;
import com.example.instagram.ui.main.searchess_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

public class main_ig extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ig);

        LoadFragment(new PlaceholderFragment());
Log.d("erere",new PlaceholderFragment().toString());

        BottomNavigationView tabs = findViewById(R.id.tabs);
        tabs.setOnNavigationItemSelectedListener(this);
        tabs.setSelectedItemId(R.id.home);
    }

    public Boolean LoadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.view_pager, fragment)
                    .commit();
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.home:
                fragment = new PlaceholderFragment();
                break;

            case R.id.searches:
                //for search
                fragment = new searchess_fragment();
                break;

            case R.id.plus:
                //new post
                startActivity( new Intent(main_ig.this,Take.class));
                break;

            case R.id.like:
                //list of following's ,follower's likes
                fragment = new likes_figment_fragment();
                break;

            case R.id.profilee:
                fragment = new profil_fragment();
                break;
        }
        return LoadFragment(fragment);
    }

}