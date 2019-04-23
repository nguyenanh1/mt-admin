package vn.anhnguyen.mt.domain.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TicketDetail implements Serializable {
    @SerializedName("ticket")
    @Expose
    private Ticket ticket;
    @SerializedName("position")
    @Expose
    private ZoomDetail position;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public ZoomDetail getPosition() {
        return position;
    }

    public void setPosition(ZoomDetail position) {
        this.position = position;
    }
}
