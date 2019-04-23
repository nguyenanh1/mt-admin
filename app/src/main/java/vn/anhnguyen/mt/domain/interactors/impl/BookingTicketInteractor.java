package vn.anhnguyen.mt.domain.interactors.impl;

import vn.anhnguyen.ticketmovie.config.CommonVls;
import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.interactors.IBookingTicketInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.base.AbstractInteractor;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyUnHolderTicket;
import vn.anhnguyen.ticketmovie.domain.model.response.BaseResponse;
import vn.anhnguyen.ticketmovie.domain.service.IAPIService;
import vn.anhnguyen.ticketmovie.domain.service.IDeviceUtils;
import vn.anhnguyen.ticketmovie.domain.service.ISharedPrefUtils;

public class BookingTicketInteractor extends AbstractInteractor implements IBookingTicketInteractor {
    private IBookingTicketInteractor.Callback mCallback;
    private IAPIService mAPIService;
    private IDeviceUtils mDeviceUtils;
    private ISharedPrefUtils mSharedPrefUtils;
    private int idTrans;

    public BookingTicketInteractor(Executor threadExecutor, MainThread mainThread, Callback mCallback,
                                   IAPIService mAPIService, IDeviceUtils mDeviceUtils,
                                   ISharedPrefUtils mSharedPrefUtils, int idTrans) {
        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.mAPIService = mAPIService;
        this.mDeviceUtils = mDeviceUtils;
        this.mSharedPrefUtils = mSharedPrefUtils;
        this.idTrans = idTrans;
    }

    @Override
    public void run() {
        if(mDeviceUtils.hasInternetConnection()) {
            String token = mSharedPrefUtils.getLoginStatusToken();
            BodyUnHolderTicket request = new BodyUnHolderTicket(idTrans);
            BaseResponse response = mAPIService.booking(token,request);
            if(response!=null){
                switch (response.getCode()){
                    case CommonVls.SUCCESS:
                        notifySuccess("Thanh toán thành công");
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

    private void notifySuccess(final String message) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.bookingSuccess(message);
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
