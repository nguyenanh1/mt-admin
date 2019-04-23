package vn.anhnguyen.mt.domain.interactors.impl;

import vn.anhnguyen.ticketmovie.config.CommonVls;
import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.interactors.ILogoutInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.base.AbstractInteractor;
import vn.anhnguyen.ticketmovie.domain.model.response.BaseResponse;
import vn.anhnguyen.ticketmovie.domain.service.IAPIService;
import vn.anhnguyen.ticketmovie.domain.service.IDeviceUtils;
import vn.anhnguyen.ticketmovie.domain.service.ISharedPrefUtils;

public class LogoutInteractor extends AbstractInteractor implements ILogoutInteractor {
    private ILogoutInteractor.Callback mCallback;
    private IAPIService mAPIService;
    private IDeviceUtils mDeviceUtils;
    private ISharedPrefUtils mSharedPrefUtils;

    public LogoutInteractor(Executor threadExecutor, MainThread mainThread, Callback mCallback, IAPIService mAPIService,
                            IDeviceUtils mDeviceUtils, ISharedPrefUtils mSharedPrefUtils) {
        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.mAPIService = mAPIService;
        this.mDeviceUtils = mDeviceUtils;
        this.mSharedPrefUtils = mSharedPrefUtils;
    }

    @Override
    public void run() {
        if(mDeviceUtils.hasInternetConnection()){
            String token = mSharedPrefUtils.getLoginStatusToken();
            BaseResponse response = mAPIService.logout(token);
            if(response!=null){
                switch (response.getCode()){
                    case CommonVls.SUCCESS:
                        mSharedPrefUtils.setLoginStatus(false);
                        notifySuccess();
                        break;
                    case CommonVls.ARGUMENT_NOT_VALID:
                        notifyError("Bạn chưa nhập đầy đủ thông tin");
                        break;
                    case CommonVls.LOGIN_OTHER_DEVICE:
                        notifyError("Tài khoản đang đăng nhập trên 1 thiết bị khác");
                        break;
                    case  CommonVls.SYSTEM_ERROR:
                        notifyError("Có lỗi từ hệ thống");
                        break;
                    case CommonVls.SESSION_TIME_OUT:
                        notifySessionTimeout(response.getMesg());
                        break;
                    case CommonVls.TOKEN_TIME_OUT:
                        notifyTokenTimeout(response.getMesg());
                        break;
                    default:
                        notifyError(response.getMesg());
                }
            }else {
                notifyNoInternet();
            }
        }
    }

    private void notifySuccess() {
                    mMainThread.post(new Runnable() {
                        @Override
                        public void run() {
                            mCallback.logoutSuccess();
            }
        });
    }

    private void notifySessionTimeout(final String message) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onSessionTimeout(message);
            }
        });
    }

    private void notifyTokenTimeout(final String message) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onTokenTimeout(message);
            }
        });
    }

    private void notifyLoginOtherDevice(final String message) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLoginOtherDevice(message);
            }
        });
    }

    private void notifyError(final String message) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFailMessage(message);
            }
        });
    }

    private void notifyNoInternet() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onNoInternetConnection("Không có kết nối mạng, vui lòng kiểm tra lại");
            }
        });
    }
}
