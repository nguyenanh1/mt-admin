package vn.anhnguyen.mt.domain.interactors;

import vn.anhnguyen.ticketmovie.domain.interactors.base.BasePresenterCallback;
import vn.anhnguyen.ticketmovie.domain.interactors.base.Interactor;

public interface IChangeProfileInteractor extends Interactor {
    interface Callback extends BasePresenterCallback{
        void changeProfileSuccess(String message);
    }
}
