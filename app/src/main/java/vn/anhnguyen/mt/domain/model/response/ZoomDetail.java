package vn.anhnguyen.mt.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ZoomDetail implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("row")
    @Expose
    private Integer row;
    @SerializedName("col")
    @Expose
    private Integer col;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("postion")
    @Expose
    private Integer postion;
    @SerializedName("room")
    @Expose
    private Integer room;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPostion() {
        return postion;
    }

    public void setPostion(Integer postion) {
        this.postion = postion;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

}
