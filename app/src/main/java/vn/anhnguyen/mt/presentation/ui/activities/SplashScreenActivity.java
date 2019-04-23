package vn.anhnguyen.mt.presentation.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.anhnguyen.mt.R;
import vn.anhnguyen.mt.util.SharePrefUtils;

public class SplashScreenActivity extends BaseActivity {
    private final static int MILLION = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        hideNavigation();
        nextActivity();
    }

    private void nextActivity() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(MILLION);
                    if(SharePrefUtils.instance().getLoginStatus()){
                        gotoHome();
                    }else {
                        gotoHome();
                        finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
