package com.alexanderthegreate.generalx.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ambitos on 25/9/2016.
 */
public class ServiceStarter  extends BroadcastReceiver {

    @Override
    public void onReceive(Context _context, Intent _intent) {

        Intent i = new Intent("com.prac.test.MyPersistingService");
        i.setClass(_context, ServiceCode.class);
        _context.startService(i);
    }

}