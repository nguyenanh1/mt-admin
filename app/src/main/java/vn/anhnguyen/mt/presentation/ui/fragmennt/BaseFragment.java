package vn.anhnguyen.mt.presentation.ui.fragmennt;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


import java.util.Objects;

import vn.anhnguyen.mt.presentation.ui.BaseView;
import vn.anhnguyen.mt.presentation.ui.activities.LoginActivity;
import vn.anhnguyen.mt.presentation.ui.activities.MainActivity;
import vn.anhnguyen.mt.util.SharePrefUtils;
import vn.anhnguyen.mt.util.common.CommonUtil;


public abstract class BaseFragment extends Fragment implements BaseView {
    protected Context context;
    protected ProgressDialog progressDialog;

    public abstract String getTitle();

    public MainActivity activity;

    @SuppressLint("NewApi")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.context = getActivity();
        progressDialog = new ProgressDialog(getContext());
        CommonUtil.hideSoftKeyboard(Objects.requireNonNull(getActivity()));
    }


    public void gotoLogin() {
        if (this.getContext() != null) {
            //Toast.makeText(this.getContext(), CommonUtil.getStringFromRes(R.string.str_session_timeout, this.getContext()), Toast.LENGTH_SHORT).show();
            SharePrefUtils.instance().setLoginStatus(false);
            startActivity(LoginActivity.getIntent(this.getActivity()));
            if (MainActivity.getInstance() != null)
                MainActivity.getInstance().finish();
        }
    }

    public void gotoLogin(String message) {
        SharePrefUtils.instance().setLoginStatus(false);
        Intent login = new Intent(context, LoginActivity.class);
        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(login);

        if (MainActivity.getInstance() != null) {
            MainActivity.getInstance().finish();
        }
//        getActivity().finish();
    }

    public void showSnackBarToast(String message) {


    }

    protected void showCustomSnackBar(String message, int backgroundColor, int textColor, int imgRes) {

    }

    protected void showNetworkSnackbar(String message) {

    }

    public void showWarning(String message) {
        if (getActivity() != null && isAdded())
            //CommonUtil.warningSnackBar(message, this.getActivity().getWindow().getDecorView().getRootView(), this.getContext());
            CommonUtil.warningSnackBar(message, this.getActivity().getWindow().getDecorView().getRootView(), this.getContext());


    }

    public void showToast(String message) {
        CommonUtil.showToast(message, this.getActivity());
    }

    public void showProgress() {
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.hide();
        }
    }

    public void showError(String message) {


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void showNoInternetSnackBar(String message) {

    }

    public void gotoHome() {

    }

}