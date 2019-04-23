package vn.anhnguyen.mt.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGetDetailMovie extends BaseResponse{
    @SerializedName("data")
    @Expose
    private MovieCategory data;

    public MovieCategory getData() {
        return data;
    }

    public void setData(MovieCategory data) {
        this.data = data;
    }
}
