package vn.anhnguyen.mt.domain.model.request;

public class BodyRegisterRequest {
    private String email;
    private String password;
    private String lastname;
    private String name;
    private Long birthday;
    private Integer gender;
    private String phone;
    private String address;
    private Integer os_id;
    private String device_id;

    public BodyRegisterRequest(String email, String password, String lastname, String name, Long birthday, Integer gender, String phone, String address, Integer os_id, String device_id) {
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.os_id = os_id;
        this.device_id = device_id;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getOs_id() {
        return os_id;
    }

    public void setOs_id(Integer os_id) {
        this.os_id = os_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
