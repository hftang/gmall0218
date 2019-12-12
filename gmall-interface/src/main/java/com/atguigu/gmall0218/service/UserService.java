package com.atguigu.gmall0218.service;

import com.atguigu.gmall0218.bean.UserAddress;
import com.atguigu.gmall0218.bean.UserInfo;

import java.util.List;

/**
 * @Auther: hftang
 * @Date: 2019/12/10 19:44
 * @Description:
 */
public interface UserService {
    List<UserInfo> findAll();

    List<UserAddress> getUserAddressList(String userId);
}
