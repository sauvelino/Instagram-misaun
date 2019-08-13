package com.example.instagram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHOlder> {

    private Context context;
    private List<Post> list;

    //pass in contex in list of tweets

    public RecyclerviewAdapter(Context context,List<Post> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_home_view,parent,false);
        return new ViewHOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHOlder holder, int position) {
        Post p=list.get(position);
        holder.username.setText(p.getUser().getUsername());
        holder.user_caption.setText(p.getUser().getUsername());
        holder.description.setText(p.getDescription());


        Glide.with(context).load(p.getImage().getUrl()).placeholder(R.drawable.color_background).into(holder.imgpost);
        //Glide.with(context).load(p.getImage().getUrl()).into(holder.imgpost);
        Glide.with(context).load(p.getParseUser("user").getParseFile("profile").getUrl()).placeholder(R.drawable.color_background).into(holder.img_profile);
        Log.d("mypictureprofile",p.getParseUser("user").getParseFile("profile").getUrl().toString());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAllToList(List<Post> mlist){
        list.clear();
        list.addAll(mlist);
        notifyDataSetChanged();
    }

    //Define the viewholder
    public class ViewHOlder extends RecyclerView.ViewHolder{

        public ImageView imgpost,img_profile;
        public TextView username,description,user_caption;


        public ViewHOlder(@NonNull View itemView) {
            super(itemView);
            img_profile=itemView.findViewById(R.id.img_profiles);
            imgpost=itemView.findViewById(R.id.img_post);
            username=itemView.findViewById(R.id.tv_username);
            description=itemView.findViewById(R.id.tv_caption);
            user_caption=itemView.findViewById(R.id.user_caption);


        }
    }
}
