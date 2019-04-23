package vn.anhnguyen.mt.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGetMovieTime extends BaseResponse{
    @SerializedName("data")
    @Expose
    private List<MovieTime> data;

    public List<MovieTime> getData() {
        return data;
    }

    public void setData(List<MovieTime> data) {
        this.data = data;
    }
}
