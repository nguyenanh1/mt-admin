package vn.anhnguyen.mt.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {
    @SerializedName("idRoom")
    @Expose
    private Integer idRoom;
    @SerializedName("nameRoom")
    @Expose
    private String nameRoom;
    @SerializedName("numRow")
    @Expose
    private Integer numRow;
    @SerializedName("numCol")
    @Expose
    private Integer numCol;

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public Integer getNumRow() {
        return numRow;
    }

    public void setNumRow(Integer numRow) {
        this.numRow = numRow;
    }

    public Integer getNumCol() {
        return numCol;
    }

    public void setNumCol(Integer numCol) {
        this.numCol = numCol;
    }
}
