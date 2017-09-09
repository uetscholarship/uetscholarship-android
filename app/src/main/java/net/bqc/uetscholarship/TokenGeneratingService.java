package net.bqc.uetscholarship;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by BQC-PC on 9/9/2017.
 */

public class TokenGeneratingService extends FirebaseInstanceIdService {

    private final String TAG = getClass().getName();

    @Override
    public void onCreate() {
        Log.d(TAG, "created service");
        super.onCreate();
    }

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token: " + token);

        // TODO: send token to server
    }
}