package com.example.instagram.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.Post;
import com.example.instagram.R;
import com.example.instagram.RecyclerviewAdapter;
import com.example.instagram.Take;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    List<Post> list;
    RecyclerviewAdapter listAdapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_ig, container,false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Toolbar toolbar = view.findViewById(R.id.toolbar_fragmenthome);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageView btn_camera=view.findViewById(R.id.camera);
        Querypost();
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Take.class));

            }
        });

        recyclerView =view.findViewById(R.id.recyclerView);
        list=new ArrayList<Post>();
        listAdapter=new RecyclerviewAdapter(getActivity(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

        recyclerView.setAdapter(listAdapter);
        fetchAllPosts();

    }

    private void Querypost() {
        ParseQuery<Post> postParseQuery=new ParseQuery<Post>(Post.class);
        postParseQuery.include(Post.Key_user);
         postParseQuery.setLimit(20);
         postParseQuery.addAscendingOrder(Post.Key_CREATE_AT);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       // inflater.inflate(R.menu.,menu);
    }

    private void fetchAllPosts() {
        ParseQuery<Post> postParseQuery=new ParseQuery<Post>(Post.class);
        postParseQuery.include(Post.Key_user);
        postParseQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if(e!=null){
                    Log.d("Error Fetch","Error while post");
                    e.printStackTrace();
                    return;
                }
            listAdapter.addAllToList(objects);
                Log.d("dataaa",listAdapter.toString());
            }
        });
    }
}