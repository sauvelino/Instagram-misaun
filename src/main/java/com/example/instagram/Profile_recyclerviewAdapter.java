package com.example.instagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Profile_recyclerviewAdapter extends RecyclerView.Adapter<Profile_recyclerviewAdapter.ViewHolder>{
    private Context context;
    private List<Post> list;


    public Profile_recyclerviewAdapter(Context context, List<Post> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.photo_item,parent,false);
        return new Profile_recyclerviewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
Post post=list.get(position);
        Glide.with(context).load(post.getImage().getUrl()).into(holder.image_item_post);
//        Glide.with(context).load(post.getParseUser("user").getParseFile("profile").getUrl()).placeholder(R.drawable.color_background).into(holder.img_profiles);
      //  holder.user_name_tv.setText(post.getUser().getUsername());
       // holder.full_name_tv.setText(post.getName());
      //  holder.bio.setText(post.getBio());
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image_item_post;
        ImageView img_profiles;
        TextView user_name_tv,full_name_tv,bio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_item_post=itemView.findViewById(R.id.image_item_post);
            img_profiles=itemView.findViewById(R.id.img_account);
            user_name_tv=itemView.findViewById(R.id.user_name_tv);
            full_name_tv=itemView.findViewById(R.id.full_name_tv);
            bio=itemView.findViewById(R.id.bio);
        }
    }
}
