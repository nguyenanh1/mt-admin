package vn.anhnguyen.mt.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ticket implements Serializable {
    @SerializedName("idTicket")
    @Expose
    private Integer idTicket;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("idMovietime")
    @Expose
    private Integer idMovietime;
    @SerializedName("idZoomDetail")
    @Expose
    private Integer idZoomDetail;

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getIdMovietime() {
        return idMovietime;
    }

    public void setIdMovietime(Integer idMovietime) {
        this.idMovietime = idMovietime;
    }

    public Integer getIdZoomDetail() {
        return idZoomDetail;
    }

    public void setIdZoomDetail(Integer idZoomDetail) {
        this.idZoomDetail = idZoomDetail;
    }
}
