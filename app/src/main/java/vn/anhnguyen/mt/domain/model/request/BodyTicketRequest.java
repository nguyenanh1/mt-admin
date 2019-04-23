package vn.anhnguyen.mt.domain.model.request;

import java.util.List;

public class BodyTicketRequest {
    private List<Integer> list;

    public BodyTicketRequest(List<Integer> list) {
        this.list = list;
    }
}
