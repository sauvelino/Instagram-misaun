package com.example.instagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String Key_description="description";
    public static final String Key_image="image";
    public static final String Key_user="user";
    public static final String Key_profile="profile";


    public String getDescription(){return getString(Key_description);}
    public void setDescription(String description){put(Key_description,description);}

    public ParseFile getImage(){return getParseFile(Key_image);}
    public void setImage(ParseFile parseFile){put(Key_image,parseFile);}

    public ParseUser getUser(){return getParseUser(Key_user);}
    public void setUser(ParseUser parseUser){put(Key_user,parseUser);}

    public ParseFile getProfile(){return getParseFile(Key_profile);}
    public void setProfile(ParseFile parseFile){put(Key_profile,parseFile);}
}
