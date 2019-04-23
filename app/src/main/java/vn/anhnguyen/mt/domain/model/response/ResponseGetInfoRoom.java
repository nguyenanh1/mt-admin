package vn.anhnguyen.mt.domain.model.response;

public class ResponseGetInfoRoom extends BaseResponse {
    private Room data;

    public Room getData() {
        return data;
    }

    public void setData(Room data) {
        this.data = data;
    }
}
