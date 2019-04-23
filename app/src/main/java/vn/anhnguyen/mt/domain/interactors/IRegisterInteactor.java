package vn.anhnguyen.mt.domain.interactors;

import vn.anhnguyen.ticketmovie.domain.interactors.base.BasePresenterCallback;
import vn.anhnguyen.ticketmovie.domain.interactors.base.Interactor;

public interface IRegisterInteactor extends Interactor {
    interface Callback extends BasePresenterCallback{
        void registerSuccess(String message);
    }
}
