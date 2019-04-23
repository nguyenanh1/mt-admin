package vn.anhnguyen.mt.domain.model.request;

public class BodyLoginRequest {
    private String email;
    private String password;
    private String device_id;
    private int os_id;

    public BodyLoginRequest() {
    }

    public BodyLoginRequest(String email, String password, String device_id, int os_id) {
        this.email = email;
        this.password = password;
        this.device_id = device_id;
        this.os_id = os_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public int getOs_id() {
        return os_id;
    }

    public void setOs_id(int os_id) {
        this.os_id = os_id;
    }
}
