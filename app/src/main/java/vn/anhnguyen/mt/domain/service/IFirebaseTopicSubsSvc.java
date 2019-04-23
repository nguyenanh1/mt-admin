/*
 * Copyright (c) 7/2018. phanpc@gmail.com
 * Last modified 6/29/18 10:33 AM
 */

package vn.anhnguyen.mt.domain.service;

import java.util.List;

public interface IFirebaseTopicSubsSvc {
    void subscribeWhenLogin(List<String> topics);

    void unSubcribeWhenLogout(List<String> topics);

    void subscribeTopicSys();

    void unSubcribeLogOut();
}
