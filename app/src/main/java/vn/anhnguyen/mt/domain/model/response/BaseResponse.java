package vn.anhnguyen.mt.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("code")
    @Expose
    protected String code;
    @SerializedName("mesg")
    @Expose
    protected String mesg;
    @SerializedName("timestamp")
    @Expose
    protected String timestamp;

    public BaseResponse() {
    }

    public BaseResponse(String code, String mesg, String timestamp) {
        this.code = code;
        this.mesg = mesg;
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
