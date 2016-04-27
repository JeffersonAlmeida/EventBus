package com.example.jeffersonalmeida.eventbusapp;

// event
public class NetworkStateChanged {

    private boolean mIsInternetConnected;

    public NetworkStateChanged(boolean isInternetConnected) {
        this.mIsInternetConnected = isInternetConnected;
    }

    public boolean isInternetConnected() {
        return this.mIsInternetConnected;
    }
}

