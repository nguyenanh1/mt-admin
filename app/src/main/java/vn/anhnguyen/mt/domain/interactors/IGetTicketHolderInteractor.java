package vn.anhnguyen.mt.domain.interactors;

import java.util.List;

import vn.anhnguyen.ticketmovie.domain.interactors.base.BasePresenterCallback;
import vn.anhnguyen.ticketmovie.domain.interactors.base.Interactor;
import vn.anhnguyen.ticketmovie.domain.model.response.TicketRoom;

public interface IGetTicketHolderInteractor extends Interactor {
    interface Callback extends BasePresenterCallback{
        void getTicketHolderSuccess(List<TicketRoom> mList);
    }
}

