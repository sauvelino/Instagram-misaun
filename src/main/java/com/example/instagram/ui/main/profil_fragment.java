package com.example.instagram.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.Post;
import com.example.instagram.Profile_recyclerviewAdapter;
import com.example.instagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class profil_fragment extends Fragment {
    RecyclerView recyclerView;
    Profile_recyclerviewAdapter profile_recyclerviewAdapter;
    List<Post> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.activity_profil_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
recyclerView=view.findViewById(R.id.profile_recycler_view);
recyclerView.setHasFixedSize(true);
        TextView username_tv,full_name_tv,bio;
        username_tv=view.findViewById(R.id.user_name_tv);
        full_name_tv=view.findViewById(R.id.full_name_tv);
        bio=view.findViewById(R.id.bio);




        LinearLayoutManager linearLayoutManager=new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(linearLayoutManager);
        list=new ArrayList<>();
        profile_recyclerviewAdapter=new Profile_recyclerviewAdapter(getContext(),list);
        recyclerView.setAdapter(profile_recyclerviewAdapter);
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
                profile_recyclerviewAdapter.addAllToList(objects);
                Log.d("dataaa_profile",profile_recyclerviewAdapter.toString());
            }
        });
    }
}
