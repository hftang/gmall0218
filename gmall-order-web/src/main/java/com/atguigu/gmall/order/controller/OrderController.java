package com.atguigu.gmall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall0218.bean.UserAddress;
import com.atguigu.gmall0218.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: hftang
 * @Date: 2019/12/11 10:55
 * @Description:
 */
@Controller
public class OrderController {

   @Reference
    private UserService userService;

    @RequestMapping("trade")
    @ResponseBody
    public List<UserAddress> trade(String userId) {

        List<UserAddress> userAddressList = this.userService.getUserAddressList(userId);

        return userAddressList;
    }


}
