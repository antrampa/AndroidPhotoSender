package com.alexanderthegreate.generalx.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by ambitos on 25/9/2016.
 */
public class ServiceCode extends IntentService {

    private Looper mServiceLooper;
    //private ServiceHandler mServiceHandler;

    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public ServiceCode() {
        super("ServiceCode");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent,flags,startId);
    }
}