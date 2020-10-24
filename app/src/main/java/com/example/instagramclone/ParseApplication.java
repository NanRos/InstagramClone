package com.example.instagramclone;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models
        ParseObject.registerSubclass(Post.class);
        // initialization code
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("mORkKCxIzTlgiZtiNiPgBbv3Gvgu9SDVPTWP28iQ")
                .clientKey("s5GyPHWRYSQIEI9ydi2X9QvHxyjeFht2uUKLo4AO")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
