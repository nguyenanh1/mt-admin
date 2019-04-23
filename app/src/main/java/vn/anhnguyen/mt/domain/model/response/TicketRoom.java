package vn.anhnguyen.mt.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TicketRoom {
    @SerializedName("ticketEntity")
    @Expose
    private Ticket ticketEntity;
    @SerializedName("zoomDetailEntity")
    @Expose
    private ZoomDetail zoomDetailEntity;

    public Ticket getTicketEntity() {
        return ticketEntity;
    }

    public void setTicketEntity(Ticket ticketEntity) {
        this.ticketEntity = ticketEntity;
    }

    public ZoomDetail getZoomDetailEntity() {
        return zoomDetailEntity;
    }

    public void setZoomDetailEntity(ZoomDetail zoomDetailEntity) {
        this.zoomDetailEntity = zoomDetailEntity;
    }
}
