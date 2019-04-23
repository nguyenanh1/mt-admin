package vn.anhnguyen.mt.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGetDetaiLMovieTime extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<TicketDetail> data = null;

    public List<TicketDetail> getData() {
        return data;
    }

    public void setData(List<TicketDetail> data) {
        this.data = data;
    }
}
