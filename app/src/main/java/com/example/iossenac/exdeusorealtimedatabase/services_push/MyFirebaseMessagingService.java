package com.example.iossenac.exdeusorealtimedatabase.services_push;

import android.graphics.Color;
import android.util.Log;

import com.example.iossenac.exdeusorealtimedatabase.Background;
import com.example.iossenac.exdeusorealtimedatabase.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * Created by iossenac on 24/06/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("PUSH", "Recebeu Push!");
        Map<String, String> mapValues = remoteMessage.getData();

        String r = mapValues.get("r");
        String g = mapValues.get("g");
        String b = mapValues.get("b");

        Background background = new Background();
        background.r = Integer.parseInt(r);
        background.g = Integer.parseInt(g);
        background.b = Integer.parseInt(b);

        EventBus.getDefault().post(background);

    }
}
