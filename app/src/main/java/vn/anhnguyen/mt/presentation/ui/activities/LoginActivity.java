package vn.anhnguyen.mt.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.anhnguyen.mt.R;

public class LoginActivity extends AppCompatActivity {

    public static Intent getIntent(Context context){
        return new Intent(context,LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
