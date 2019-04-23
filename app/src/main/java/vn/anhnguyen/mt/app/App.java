package vn.anhnguyen.mt.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;

import vn.anhnguyen.mt.domain.excutor.Executor;
import vn.anhnguyen.mt.domain.excutor.MainThread;
import vn.anhnguyen.mt.domain.excutor.impl.ThreadExecutor;
import vn.anhnguyen.mt.presentation.ui.MainThreadImpl;
import vn.anhnguyen.mt.service.DeviceUtils;
import vn.anhnguyen.mt.service.LifecycleAppListener;
import vn.anhnguyen.mt.util.SharePrefUtils;


public class App extends MultiDexApplication {
    private LifecycleAppListener lifecycleAppListener;
    protected Executor mExecutor = ThreadExecutor.getInstance();
    protected MainThread mMainThread = MainThreadImpl.getInstance();

    private Activity currentAcc;

    @Override
    public void onCreate() {
        super.onCreate();
        //Fabric.with(this, new Crashlytics());
        SharePrefUtils.instance().init(getBaseContext());
        DeviceUtils.instance().init(getBaseContext());
        getKeyhash();
        lifecycleAppListener = new LifecycleAppListener(getBaseContext());
        setupLifecycleListener();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }


    @SuppressLint("PackageManagerGetSignatures")
    void getKeyhash() {
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.dts.tptt", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), Base64.DEFAULT));
                Log.e("keyhash", something);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String something = new String(Base64.encodeBytes(md.digest()));
    }

    private void setupLifecycleListener() {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(lifecycleAppListener);
    }

}
