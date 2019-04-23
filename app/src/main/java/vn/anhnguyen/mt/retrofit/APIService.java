package vn.anhnguyen.mt.retrofit;

import retrofit2.Call;
import retrofit2.Response;
import vn.anhnguyen.mt.domain.service.IAPIService;


public class APIService implements IAPIService {

    private static APIService sInstance;

    public static APIService getInstance() {
        if (sInstance == null) {
            sInstance = new APIService();
        }
        return sInstance;
    }


}
