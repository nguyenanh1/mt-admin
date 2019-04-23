/*
 * Copyright (c) 7/2018. phanpc@gmail.com
 * Last modified 7/1/18 12:59 AM
 */

package vn.anhnguyen.mt.domain.interactors.base;

public interface BasePresenterCallback {
    void onSessionTimeout(String message);

    void onTokenTimeout(String message);

    void onLoginOtherDevice(String message);

    void onNoInternetConnection(String message);

    void onFailMessage(String message);
}
