package vn.anhnguyen.mt.domain.interactors;

import vn.anhnguyen.ticketmovie.domain.interactors.base.BasePresenterCallback;
import vn.anhnguyen.ticketmovie.domain.interactors.base.Interactor;
import vn.anhnguyen.ticketmovie.domain.model.response.TransMovie;

public interface IHoldTicketInteractor extends Interactor {
    interface Callback extends BasePresenterCallback{
        void holdSucess(TransMovie transMovie);

        void holdFail(String message);
    }
}
