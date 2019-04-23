package vn.anhnguyen.mt.domain.interactors.impl;

import java.util.List;

import vn.anhnguyen.ticketmovie.config.CommonVls;
import vn.anhnguyen.ticketmovie.domain.excutor.Executor;
import vn.anhnguyen.ticketmovie.domain.excutor.MainThread;
import vn.anhnguyen.ticketmovie.domain.interactors.IGetMovieTopInteractor;
import vn.anhnguyen.ticketmovie.domain.interactors.base.AbstractInteractor;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetMovie;
import vn.anhnguyen.ticketmovie.domain.service.IAPIService;
import vn.anhnguyen.ticketmovie.domain.service.IDeviceUtils;
import vn.anhnguyen.ticketmovie.domain.service.ISharedPrefUtils;

public class GetMovieTopInteractor extends AbstractInteractor implements IGetMovieTopInteractor {
    private IGetMovieTopInteractor.Callback mCallback;
    private IAPIService mAPIService;
    private IDeviceUtils mDeviceUtils;
    private ISharedPrefUtils mSharedPrefUtils;

    public GetMovieTopInteractor(Executor threadExecutor, MainThread mainThread, IGetMovieTopInteractor.Callback mCallback, IAPIService mAPIService,
                              IDeviceUtils mDeviceUtils, ISharedPrefUtils mSharedPrefUtils) {
        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.mAPIService = mAPIService;
        this.mDeviceUtils = mDeviceUtils;
        this.mSharedPrefUtils = mSharedPrefUtils;
    }

    @Override
    public void run() {
        if(mDeviceUtils.hasInternetConnection()) {
            ResponseGetMovie response = mAPIService.getTopMovie();
            if(response!=null){
                switch (response.getCode()){
                    case CommonVls.SUCCESS:
                        notifySuccess(response.getData());
                        break;
                    case CommonVls.LIST_EMPTY:
                        notifyError("Không có dữ liệu!");
                        break;
                    case CommonVls.SYSTEM_ERROR:
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

    private void notifySuccess(final List<MovieCategory> movie) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.getTopMovieSuccess(movie);
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
