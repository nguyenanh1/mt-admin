package vn.anhnguyen.mt.domain.service;

public interface ISharedPrefUtils {
    void setLoginStatus(Boolean loginStatus);

    Boolean getLoginStatus();

    void setLoginStatusToken(String token);

    String getLoginStatusToken();

    void setUserName(String userName);

    String getUserName();

    int getUserId();

    void setUserId(int userId);

    void setAvatar(String avatar_url);

    String getAvatar();

    void setName(String Name);

    String getName();

    void setLastname(String lastname);

    String getLastname();

    void setAccountType(int type);

    int getAccountType();

    void setBalance(int getBalance);

    int getBalance();

    void setPoint(int point);

    int getPoint();
}
