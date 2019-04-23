package vn.anhnguyen.mt.presentation.ui;


/*
 * Copyright (c) 7/2018. phanpc@gmail.com
 * Last modified 6/25/18 8:53 AM
 */

import android.os.Handler;
import android.os.Looper;

import vn.anhnguyen.mt.domain.excutor.MainThread;

/**
 * This class makes sure that the runnable we provide will be run on the main UI thread.
 */
public class MainThreadImpl implements MainThread {

    private static MainThread sMainThread;

    private Handler mHandler;

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {

            sMainThread = new MainThreadImpl();
        }

        return sMainThread;
    }

}