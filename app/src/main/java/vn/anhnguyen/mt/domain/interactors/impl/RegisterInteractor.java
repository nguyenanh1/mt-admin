package vn.anhnguyen.mt.domain.interactors.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.anhnguyen.ticketmovie.config.AppConfig;
import vn.anhnguyen.ticketmovie.config.CommonVls;
import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.interactors.IRegisterInteactor;
import vn.anhnguyen.ticketmovie.domain.interactors.base.AbstractInteractor;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyRegisterRequest;
import vn.anhnguyen.ticketmovie.domain.model.response.BaseResponse;
import vn.anhnguyen.ticketmovie.domain.service.IAPIService;
import vn.anhnguyen.ticketmovie.domain.service.IDeviceUtils;
import vn.anhnguyen.ticketmovie.domain.service.ISharedPrefUtils;
import vn.anhnguyen.ticketmovie.util.SharePrefUtils;

public class RegisterInteractor extends AbstractInteractor implements IRegisterInteactor {
    private IRegisterInteactor.Callback mCallback;
    private IAPIService mAPIService;
    private IDeviceUtils mDeviceUtils;
    private ISharedPrefUtils mSharedPrefUtils;
    private String email;
    private String password;
    private String rePassword;
    private String lastname;
    private String name;
    private long birthday;
    private int gender;
    private String phone;
    private String address;

    public RegisterInteractor(Executor threadExecutor, MainThread mainThread, Callback mCallback,
                              IAPIService mAPIService, IDeviceUtils mDeviceUtils, ISharedPrefUtils mSharedPrefUtils,
                              String email, String password, String rePassword, String lastname, String name,
                              long birthday, int gender, String phone, String address) {
        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.mAPIService = mAPIService;
        this.mDeviceUtils = mDeviceUtils;
        this.mSharedPrefUtils = mSharedPrefUtils;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.lastname = lastname;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public void run() {
        register(email,password,rePassword,lastname,name,birthday,gender,phone,address);
    }

    private void register(String email, String password, String rePassword, String lastname, String name, long birthday, int gender, String phone, String address){
        Pattern regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = regex.matcher(email);
        if(email.isEmpty()||password.isEmpty()||rePassword.isEmpty()||lastname.isEmpty()||name.isEmpty()||phone.isEmpty()||address==null){
            notifyError("Bạn cần nhập đầy đủ thông tin");
        }else {
            if(!matcher.find()){
                notifyError("Bạn chưa nhập đúng định dạng email!");
            }else {
                if(!password.equalsIgnoreCase(rePassword)){
                    notifyError("Mật khẩu nhập lại chưa đúng");
                }else {
                    if(mDeviceUtils.hasInternetConnection()){
                        String passMd5 = CommonVls.md5(password);
                        BodyRegisterRequest request = new BodyRegisterRequest(email,passMd5,lastname,name,
                                birthday,gender,phone,address, AppConfig.OS_ANDROID,mDeviceUtils.getDeviceId());
                        BaseResponse response =mAPIService.register(request);
                        if(response!=null){
                            switch (response.getCode()){
                                case CommonVls.SUCCESS:
                                    SharePrefUtils.instance().setUserName(email);
                                    notifySuccess("Đăng ký thành công");
                                    break;
                                case CommonVls.ARGUMENT_NOT_VALID:
                                    notifyError("Bạn chưa nhập đầy đủ thông tin");
                                    break;
                                case  CommonVls.USER_REGISTER_EXITS:
                                    notifyError("Email này đã được sử dụng");
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

    }

    private void notifySuccess(final String message) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.registerSuccess(message);
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
