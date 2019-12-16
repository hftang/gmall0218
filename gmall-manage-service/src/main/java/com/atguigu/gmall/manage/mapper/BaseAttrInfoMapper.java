package com.atguigu.gmall.manage.mapper;

import com.atguigu.gmall0218.bean.BaseAttrInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: hftang
 * @Date: 2019/12/12 19:28
 * @Description:
 */
public interface BaseAttrInfoMapper extends Mapper<BaseAttrInfo> {

    List<BaseAttrInfo> getBaseAttrInfoListByCatalog3Id(String catalog3Id);
}
