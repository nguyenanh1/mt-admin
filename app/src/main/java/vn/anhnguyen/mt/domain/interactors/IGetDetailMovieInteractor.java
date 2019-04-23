package vn.anhnguyen.mt.domain.interactors;

import vn.anhnguyen.ticketmovie.domain.interactors.base.BasePresenterCallback;
import vn.anhnguyen.ticketmovie.domain.interactors.base.Interactor;
import vn.anhnguyen.ticketmovie.domain.model.response.MovieCategory;

public interface IGetDetailMovieInteractor extends Interactor {
    interface Callback extends BasePresenterCallback{
        void getDetailMovieSuccess(MovieCategory movieCategory);
    }
}
