package com.example.iossenac.exdeusorealtimedatabase;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by iossenac on 24/06/17.
 */

public class ControlLifeCycleAllApp extends Application {

    public static DatabaseReference myRef;

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }
}
