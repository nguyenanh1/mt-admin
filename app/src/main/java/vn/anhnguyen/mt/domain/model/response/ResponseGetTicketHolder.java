package vn.anhnguyen.mt.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseGetTicketHolder extends BaseResponse {
    @SerializedName("data")
    @Expose
    private List<TicketRoom> data = null;

    public List<TicketRoom> getData() {
        return data;
    }

    public void setData(List<TicketRoom> data) {
        this.data = data;
    }
}

