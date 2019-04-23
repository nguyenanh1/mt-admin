package vn.anhnguyen.mt.service;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import vn.anhnguyen.ticketmovie.config.AppConfig;

public class LifecycleAppListener implements LifecycleObserver {

    private Context context;

    public LifecycleAppListener(Context context) {
        this.context = context;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onMoveToForeground() {
        Log.d("SampleLifecycle", "Moving to foreground…");

    }

    @SuppressLint("WrongConstant")
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onMoveToBackground() {
        Log.d("SampleLifecycle", "Moving to background…");

        Intent intent = new Intent(AppConfig.FLAG_FROM_BACKGROUND);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

}
