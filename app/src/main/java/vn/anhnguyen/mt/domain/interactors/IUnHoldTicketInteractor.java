package vn.anhnguyen.mt.domain.interactors;

import vn.anhnguyen.ticketmovie.domain.interactors.base.BasePresenterCallback;
import vn.anhnguyen.ticketmovie.domain.interactors.base.Interactor;

public interface IUnHoldTicketInteractor extends Interactor {
    interface Callback extends BasePresenterCallback{
        void unHolderSucesss();

        void unHolderFail(String message);
    }
}
