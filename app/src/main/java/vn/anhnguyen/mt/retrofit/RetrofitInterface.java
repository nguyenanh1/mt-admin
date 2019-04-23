package vn.anhnguyen.mt.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.anhnguyen.mt.config.CommonVls;

public class RetrofitInterface {
    private RetrofitInterface mIntance;

    public RetrofitInterface getIntance() {
        if (mIntance == null) {
            mIntance = new RetrofitInterface();
        }
        return mIntance;
    }

    private static final OkHttpClient okHttpAdmin = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).build();

    private static API sAPI;

    public static API getAPIService() {
        if (sAPI == null) {
            String url = CommonVls.BASE_URL;

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpAdmin)
                    .build();
            sAPI = retrofit.create(API.class);

        }
        return sAPI;
    }
}
