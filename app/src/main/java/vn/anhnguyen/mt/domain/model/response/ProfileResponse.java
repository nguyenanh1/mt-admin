package vn.anhnguyen.mt.domain.model.response;

public class ProfileResponse extends BaseResponse {
    private User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
