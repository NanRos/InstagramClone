package com.example.instagramclone;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.Locale;

@ParseClassName("Post")
public class Post extends ParseObject {

    // Ensure that your subclass has a public default constructor
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_PROFILE_IMAGE = "ProfileImage";

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }
    public void setDescription(String description){
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }
    public void setImage(ParseFile parseFile){
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser parseUser){
        put(KEY_USER,parseUser);
    }

    public ParseFile getProfileImage() {
        return getUser().getParseFile(KEY_PROFILE_IMAGE);
    }
    public void setProfileImage(ParseFile parseFile){
        getUser().put(KEY_PROFILE_IMAGE, parseFile);
    }
    public String getDateCreated() {
        String Format = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat format = new SimpleDateFormat(Format, Locale.ENGLISH);
        String strDate = format.format(getCreatedAt());
        return TimeFormatter.getTimeDifference(strDate);
    }
}
