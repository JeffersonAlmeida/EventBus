package com.example.jeffersonalmeida.eventbusapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by jeffersonalmeida on 2/5/16.
 */
public class NetworkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("onReceive","onReceive");

        if (intent.getExtras() != null) {
            NetworkInfo ni = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
                // there is Internet connection
                EventBus.getDefault().post(new NetworkStateChanged(true));
                Log.e("si","si");
            } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
                // no Internet connection, send network state changed
                EventBus.getDefault().post(new NetworkStateChanged(false));
                Log.e("no","no");
            }
        }
    }


}
