package vn.anhnguyen.mt.domain.interactors;

import java.util.List;

import vn.anhnguyen.ticketmovie.domain.interactors.base.BasePresenterCallback;
import vn.anhnguyen.ticketmovie.domain.interactors.base.Interactor;
import vn.anhnguyen.ticketmovie.domain.model.response.TicketDetail;

public interface IGetDetailMovieTimeInteractor extends Interactor {
    interface Callback extends BasePresenterCallback{
        void getDetailMovieTimeSuccwss(List<TicketDetail> mList);
    }
}
