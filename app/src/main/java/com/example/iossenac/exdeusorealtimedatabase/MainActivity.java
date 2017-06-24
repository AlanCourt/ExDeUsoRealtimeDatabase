package com.example.iossenac.exdeusorealtimedatabase;

import android.graphics.Color;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "EXDOUSOFIREBASE";

    private LinearLayout ctnMain;
    public TextView textview1, textview2, textview3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ctnMain = (LinearLayout) findViewById(R.id.ctnMain);
        textview1 = (TextView) findViewById(R.id.text1);
        textview2 = (TextView) findViewById(R.id.text2);
        textview3 = (TextView) findViewById(R.id.text3);

        ControlLifeCycleAllApp.myRef.child("background").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Background value = dataSnapshot.getValue(Background.class);
                Log.d(TAG, "R: " + value.r);
                Log.d(TAG, "G: " + value.g);
                Log.d(TAG, "B: " + value.b);

                ctnMain.setBackgroundColor(Color.rgb(value.r, value.g, value.b));

                textview1.setText("R " + String.valueOf(value.r));
                textview2.setText("G " + String.valueOf(value.g));
                textview3.setText("B " + String.valueOf(value.b));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Background background = new Background();

        background.r = (int) (Math.random() * 255);
        background.g = (int) (Math.random() * 255);
        background.b = (int) (Math.random() * 255);

        ControlLifeCycleAllApp.myRef.child("background").setValue(
                background,
                new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                    }
                });

        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Background bck) {
        ctnMain.setBackgroundColor(Color.rgb(bck.r, bck.g, bck.b));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}