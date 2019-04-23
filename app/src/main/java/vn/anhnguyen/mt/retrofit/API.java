package vn.anhnguyen.mt.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyChangePasswordRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyChangeProfileRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyLoginRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyRegisterRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyTicketRequest;
import vn.anhnguyen.ticketmovie.domain.model.request.BodyUnHolderTicket;
import vn.anhnguyen.ticketmovie.domain.model.response.BaseResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.LoginResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.ProfileResponse;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetDetaiLMovieTime;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetDetailMovie;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetInfoRoom;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetListTran;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetMovie;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetMovieTime;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetTicketHolder;
import vn.anhnguyen.ticketmovie.domain.model.response.ResponseGetTransaction;

public interface API {
    //Đăng ký
    @POST("/authenication/user/register")
    Call<BaseResponse> register(@Body BodyRegisterRequest bodyRegisterRequest);

    //Đăng nhập
    @POST("/authenication/user/login")
    Call<LoginResponse> login(@Body BodyLoginRequest bodyLoginRequest);

    //Get Profile
    @GET("/authenication/user/profile")
    Call<ProfileResponse> getProfile(@Header("token") String token);

    //Change Profile
    @PUT("/authenication/user/change-profile")
    Call<BaseResponse> changeProfile(@Header("token") String token,
                                     @Body BodyChangeProfileRequest bodyChangeProfileRequest);

    //Change Passowrd
    @PUT("/authenication/user/change-password")
    Call<BaseResponse> changePassword(@Header("token") String token,
                                      @Body BodyChangePasswordRequest bodyChangePassword);

    //Logout
    @POST("/authenication/user/logout")
    Call<BaseResponse> logout(@Header("token") String token);

    //GET top 3 movie
    @GET("/user/movie/getTop")
    Call<ResponseGetMovie> getTopMovie();

    //GET is Showing movie
    @GET("/user/movie/getIsShowing")
    Call<ResponseGetMovie> getIsShowingMovie(@Query("partdate") int partdate,
                                             @Query("start") int start,
                                             @Query("limit") int limit);

    //Get Comming soon movie
    @GET("/user/movie/getCommingSoon")
    Call<ResponseGetMovie> getCommingSoonMovie(@Query("partdate") int partdate,
                                               @Query("start") int start,
                                               @Query("limit") int limit);

    // Xem chi tiết phim
    @GET("/user/movie/getDetail")
    Call<ResponseGetDetailMovie> getMovieDetail(@Query("id") int id);

    //Lấy lịch chiếu
    @GET("/user/movietime/get")
    Call<ResponseGetMovieTime> getMovieTime(@Header("token") String token,
                                            @Query("idMovie") Integer idMovie,
                                            @Query("date") Integer date);

    //Lấy chi tiết lịch chiếu
    @GET("/user/ticket/get")
    Call<ResponseGetDetaiLMovieTime> getTicket(@Header("token") String token,
                                               @Query("idMovieTime") Integer idMovieTime);

    //Get thông tin phòng
    @GET("/user/room/get")
    Call<ResponseGetInfoRoom> getRoom(@Header("token") String token,
                                      @Query("idRoom") Integer idRoom);

    @PUT("/user/ticket/hold")
    Call<ResponseGetTransaction> hold(@Header("token") String token,
                                      @Body BodyTicketRequest bodyTicket);

    @PUT("/user/ticket/unhold")
    Call<BaseResponse> unhold(@Header("token") String token,
                              @Body BodyUnHolderTicket bodyTicket);

    @PUT("/user/ticket/booking")
    Call<BaseResponse> booking(@Header("token") String token,
                               @Body BodyUnHolderTicket bodyTicket);

    @GET("/user/ticket/getTicketBook")
    Call<ResponseGetTicketHolder> getTicketHolder(@Header("token") String token,
                                                  @Query("idTrans") int idTrans);

    @GET("/user/ticket/history-transaction")
    Call<ResponseGetListTran> getTransaction(@Header("token") String token);
}
