package vn.anhnguyen.mt.presentation.ui.activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.anhnguyen.mt.R;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    private static MainActivity sInstance;

    public static MainActivity getInstance() {
        return sInstance;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
