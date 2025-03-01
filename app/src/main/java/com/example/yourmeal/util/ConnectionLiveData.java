package com.example.yourmeal.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

public class ConnectionLiveData extends LiveData<Boolean> {
    private final ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback connectivityManagerCallback;

    public ConnectionLiveData(Context context) {
        this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    protected void setValue(Boolean value) {
        if (getValue() != null && getValue().equals(value)) {
            return;
        }
        super.setValue(value);
    }

    @Override
    protected void onActive() {
        super.onActive();
        updateConnection();
        connectivityManagerCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            @WorkerThread
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities);
                if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                        networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                    postValue(true);
                }
            }

            @Override
            @WorkerThread
            public void onLost(Network network) {
                postValue(false);
            }
        };

        connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallback);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        connectivityManager.unregisterNetworkCallback(connectivityManagerCallback);
    }

    @SuppressWarnings("deprecation")
    private void updateConnection() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        setValue(activeNetwork != null && activeNetwork.isConnected());
    }
}
