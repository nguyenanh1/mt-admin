package vn.anhnguyen.mt.domain.interactors.impl;

import java.util.List;

import vn.anhnguyen.ticketmovie.config.CommonVls;
import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.interactors.IHoldTicketInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.base.AbstractInteractor;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyTicketRequest;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetTransaction;
import vn.anhnguyen.ticketmovie.domain.model.response.TransMovie;
import vn.anhnguyen.ticketmovie.domain.service.IAPIService;
import vn.anhnguyen.ticketmovie.domain.service.IDeviceUtils;
import vn.anhnguyen.ticketmovie.domain.service.ISharedPrefUtils;

public class HoldTicketInteractor extends AbstractInteractor implements IHoldTicketInteractor {
    private IHoldTicketInteractor.Callback mCallback;
    private IAPIService mAPIService;
    private IDeviceUtils mDeviceUtils;
    private ISharedPrefUtils mSharedPrefUtils;
    private List<Integer> mList;

    public HoldTicketInteractor(Executor threadExecutor, MainThread mainThread, Callback mCallback, IAPIService mAPIService, IDeviceUtils mDeviceUtils, ISharedPrefUtils mSharedPrefUtils, List<Integer> mList) {
        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.mAPIService = mAPIService;
        this.mDeviceUtils = mDeviceUtils;
        this.mSharedPrefUtils = mSharedPrefUtils;
        this.mList = mList;
    }

    @Override
    public void run() {
        if(mDeviceUtils.hasInternetConnection()) {
            String token = mSharedPrefUtils.getLoginStatusToken();
            BodyTicketRequest request = new BodyTicketRequest(mList);
            ResponseGetTransaction response = mAPIService.hold(token,request);
            if(response!=null){
                switch (response.getCode()){
                    case CommonVls.SUCCESS:
                        notifySuccess(response.getData());
                        break;
                    case CommonVls.TICKET_IS_HOLDING:
                        notifyHoldFail("Ghế này đã được đặt");
                        break;
                    case CommonVls.ARGUMENT_NOT_VALID:
                        notifyError("Bạn chưa nhập đầy đủ thông tin");
                        break;
                    case CommonVls.LOGIN_OTHER_DEVICE:
                        notifyLoginOtherDevice("Tài khoản của bạn đăng nhập ở 1 thiết bị khác");
                        break;
                    case CommonVls.TOKEN_IN_VALID:
                        notifyTokenTimeout("Phiên đăng nhập hết bạn");
                        break;
                    case CommonVls.TOKEN_IS_EMPTY:
                        notifyTokenTimeout("Phiên đăng nhập hết bạn");
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

    private void notifySuccess(final TransMovie transMovie) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.holdSucess(transMovie);
            }
        });
    }

    private void notifyHoldFail(final String message){
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.holdFail(message);
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
