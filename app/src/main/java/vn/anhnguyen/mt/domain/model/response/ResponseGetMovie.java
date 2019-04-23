package vn.anhnguyen.mt.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGetMovie extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<MovieCategory> data = null;

    public List<MovieCategory> getData() {
        return data;
    }

    public void setData(List<MovieCategory> data) {
        this.data = data;
    }
}
