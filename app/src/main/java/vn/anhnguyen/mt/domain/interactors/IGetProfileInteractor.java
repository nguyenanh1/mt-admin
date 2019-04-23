package vn.anhnguyen.mt.domain.interactors;

import vn.anhnguyen.ticketmovie.domain.interactors.base.BasePresenterCallback;
import vn.anhnguyen.ticketmovie.domain.interactors.base.Interactor;
import vn.anhnguyen.ticketmovie.domain.model.response.User;

public interface IGetProfileInteractor extends Interactor {
    interface Calllback extends BasePresenterCallback{
        void getProfileSuccess(User user);
    }
}
