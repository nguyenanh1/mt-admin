package vn.anhnguyen.mt.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieTime implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("timeStart")
    @Expose
    private Integer timeStart;
    @SerializedName("dateStart")
    @Expose
    private Integer dateStart;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("idMovie")
    @Expose
    private Integer idMovie;
    @SerializedName("idRoom")
    @Expose
    private Integer idRoom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Integer timeStart) {
        this.timeStart = timeStart;
    }

    public Integer getDateStart() {
        return dateStart;
    }

    public void setDateStart(Integer dateStart) {
        this.dateStart = dateStart;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }
}
