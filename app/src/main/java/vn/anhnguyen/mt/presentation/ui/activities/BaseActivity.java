package vn.anhnguyen.mt.presentation.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.anhnguyen.mt.presentation.ui.BaseView;
import vn.anhnguyen.mt.util.SharePrefUtils;
import vn.anhnguyen.mt.util.common.CommonUtil;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideKeyboard(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang xử lý, vui lòng đợi...");
        progressDialog.setCancelable(false);
    }

    protected void hideNavigation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow(); // in Activity's onCreate() for instance
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(flags);
                    }
                }
            });
        }
    }

    @Override
    public void showProgress() {
        if(progressDialog!=null){
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showWarning(String message) {

    }

    public void showToast(String message){
        CommonUtil.showToast(message,this);
    }

    public void gotoHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void showAlertDialog() {

    }

    @Override
    public void gotoLogin(boolean fromHome) {
        SharePrefUtils.instance().setLoginStatus(false);
        startActivity(LoginActivity.getIntent(this));
    }

    @Override
    public void gotoLogin(boolean fromHome,String message) {
        showToast(message);
        gotoLogin(fromHome);
    }

    @Override
    public void showSnackBarToast(String message) {

    }

    @Override
    public void showNoInternetSnackBar(String message) {

    }
}
