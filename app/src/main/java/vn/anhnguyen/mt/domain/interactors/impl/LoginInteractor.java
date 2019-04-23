package vn.anhnguyen.mt.domain.interactors.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.anhnguyen.ticketmovie.config.AppConfig;
import vn.anhnguyen.ticketmovie.config.CommonVls;
import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.interactors.ILoginInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.base.AbstractInteractor;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyLoginRequest;
import vn.anhnguyen.ticketmovie.domain.model.response.LoginResponse;
import vn.anhnguyen.ticketmovie.domain.service.IAPIService;
import vn.anhnguyen.ticketmovie.domain.service.IDeviceUtils;
import vn.anhnguyen.ticketmovie.domain.service.ISharedPrefUtils;

public class LoginInteractor extends AbstractInteractor implements ILoginInteractor {
    private ILoginInteractor.Callback mCallback;
    private IAPIService mAPIService;
    private IDeviceUtils mDeviceUtils;
    private ISharedPrefUtils mSharedPrefUtils;
    private String email;
    private String password;

    public LoginInteractor(Executor threadExecutor, MainThread mainThread, ILoginInteractor.Callback mCallback,
                           IAPIService mAPIService, IDeviceUtils mDeviceUtils,
                           ISharedPrefUtils mSharedPrefUtils, String email, String password) {
        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.mAPIService = mAPIService;
        this.mDeviceUtils = mDeviceUtils;
        this.mSharedPrefUtils = mSharedPrefUtils;
        this.email = email;
        this.password = password;
    }

    @Override
    public void run() {
        register(email,password);
    }

    private void register(String email, String password) {
        Pattern regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = regex.matcher(email);
        if(email.isEmpty()||password.isEmpty()){
            notifyError("Bạn cần nhập đầy đủ thông tin");
        }else {
            if(!matcher.find()){
                notifyError("Bạn chưa nhập đúng định dạng email!");
            }else {
                if(mDeviceUtils.hasInternetConnection()) {
                    String passMd5 = CommonVls.md5(password);
                    BodyLoginRequest bodyLoginRequest = new BodyLoginRequest(email,passMd5,mDeviceUtils.getDeviceId(), AppConfig.OS_ANDROID);
                    LoginResponse response = mAPIService.login(bodyLoginRequest);
                    if(response!=null){
                        switch (response.getCode()){
                            case CommonVls.SUCCESS:
                                mSharedPrefUtils.setUserId(response.getData().getId());
                                mSharedPrefUtils.setUserName(response.getData().getEmail());
                                mSharedPrefUtils.setAvatar(response.getData().getAvatar());
                                mSharedPrefUtils.setLastname(response.getData().getLastname());
                                mSharedPrefUtils.setName(response.getData().getName());
                                mSharedPrefUtils.setAccountType(response.getData().getType());
                                mSharedPrefUtils.setBalance(response.getData().getBalace());
                                mSharedPrefUtils.setPoint(response.getData().getPoint());
                                mSharedPrefUtils.setLoginStatusToken(response.getData().getToken());
                                mSharedPrefUtils.setLoginStatus(true);
                                notifySuccess("Đăng nhập thành công!");
                                break;
                            case CommonVls.ARGUMENT_NOT_VALID:
                                notifyError("Bạn chưa nhập đầy đủ thông tin");
                                break;
                            case  CommonVls.USER_NOT_EXITS:
                                notifyError("Tài khoản này không tồn tại");
                                break;
                            case CommonVls.PASSWORD_WRONG:
                                notifyError("Sai mật khẩu");
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
                    }
                }else {
                    notifyNoInternet();
                }
            }
        }
    }

    private void notifySuccess(final String message) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.loginSuccess(message);
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
