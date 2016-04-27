package com.example.jeffersonalmeida.eventbusapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by jeffersonalmeida on 4/27/16.
 */
public class NetworkChecker {

    private static Context context;

    public NetworkChecker(Context context) {
        this.context = context;
    }

    public boolean connected(){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

}