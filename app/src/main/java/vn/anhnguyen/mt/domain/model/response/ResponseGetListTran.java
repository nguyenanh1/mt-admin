package vn.anhnguyen.mt.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGetListTran extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<TransMovie> data = null;

    public List<TransMovie> getData() {
        return data;
    }

    public void setData(List<TransMovie> data) {
        this.data = data;
    }
}
