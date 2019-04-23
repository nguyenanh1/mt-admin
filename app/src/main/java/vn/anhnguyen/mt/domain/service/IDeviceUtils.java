/*
 * Copyright (c) 7/2018. phanpc@gmail.com
 * Last modified 6/28/18 10:43 AM
 */

package vn.anhnguyen.mt.domain.service;

public interface IDeviceUtils {
    boolean hasInternetConnection();
    boolean hasGPSEnabled();

    boolean checkConnectWifi();
    String getDeviceId();
    int getSDKVersion();
    String getCurrentSSID();
    int getHeightScreen();
}
