package net.bqc.uetscholarship;

import android.app.Application;

/**
 * Created by BQC-PC on 9/10/2017.
 */

public class UETScholarShipApp extends Application {

    private static UETScholarShipApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized UETScholarShipApp getInstance() {
        return mInstance;
    }

    public void setConnectionListener(ConnectionReceiver.ConnectionReceiverListener listener) {
        ConnectionReceiver.connectionReceiverListener = listener;
    }
}
