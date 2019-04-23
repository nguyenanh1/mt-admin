/*
 * Copyright (c) 6/2018. phanpc@gmail.com
 * Last modified 6/28/18 10:05 AM
 */

package vn.anhnguyen.mt.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import vn.anhnguyen.ticketmovie.domain.service.IDeviceUtils;


public class DeviceUtils implements IDeviceUtils {
    private Context context;

    private static DeviceUtils instance_;

    public static DeviceUtils instance() {
        if (instance_ == null)
            instance_ = new DeviceUtils();

        return instance_;
    }

    public void init(Context context_) {
        this.context = context_;
    }

    private DeviceUtils() {
    }

    public boolean hasInternetConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
        return connected;
    }

    public boolean hasGPSEnabled() {
        String provider = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (provider.contains("gps") || provider.contains("network"))
            return true;
        // otherwise return false
        return false;
    }

    public boolean checkConnectWifi() {
        boolean isConnected = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (wifi.isConnected()) {
            isConnected = true;
        }

        return isConnected;
    }

    @Override
    public String getDeviceId() {
        @SuppressLint("HardwareIds") String deviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        return deviceId;
    }

    @Override
    public int getSDKVersion() {
        int sdkVer = Build.VERSION.SDK_INT;
        return sdkVer;
    }

    @Override
    public String getCurrentSSID() {
        String ssid = null;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            @SuppressLint("WifiManagerPotentialLeak") final WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
                ssid = connectionInfo.getSSID();
            }
        }
        return ssid;
    }

    @Override
    public int getHeightScreen() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        assert windowManager != null;
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (displayMetrics.heightPixels / density);
    }
}
