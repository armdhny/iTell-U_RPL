package com.example.ari.itellu;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by Tavs on 11/05/2018.
 */

public class iTelUFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "iTelUFirebaseIIDService";
    private static final String ITELU_ENGAGE_TOPIC = "i_Tel_u";

    /**
     * The Application's current Instance ID token is no longer valid
     * and thus a new one must be requested.
     */
    @Override
    public void onTokenRefresh() {
        // If you need to handle the generation of a token, initially or
        // after a refresh this is where you should do that.
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "FCM Token: " + token);

        // Once a token is generated, we subscribe to topic.
        FirebaseMessaging.getInstance()
                .subscribeToTopic(ITELU_ENGAGE_TOPIC);
    }
}
