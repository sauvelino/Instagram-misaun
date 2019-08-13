package com.example.instagram.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.Post;
import com.example.instagram.R;
import com.example.instagram.RecyclerviewAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    //private static final String ARG_SECTION_NUMBER = "section_number";
    List<Post> list;
    RecyclerviewAdapter listAdapter;
    RecyclerView recyclerView;
/*
    public static PlaceholderFragment newInstance(int index) {
      //  PlaceholderFragment fragment = new PlaceholderFragment();
      //  Bundle bundle = new Bundle();
      //  bundle.putInt(ARG_SECTION_NUMBER, index);
      //  fragment.setArguments(bundle);
        return fragment;
    }*/


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_ig, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView =view.findViewById(R.id.recyclerView);
        list=new ArrayList<Post>();
        listAdapter=new RecyclerviewAdapter(getActivity(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        //Log.d("Error Fetch",listAdapter.toString());

        recyclerView.setAdapter(listAdapter);
        fetchAllPosts();

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