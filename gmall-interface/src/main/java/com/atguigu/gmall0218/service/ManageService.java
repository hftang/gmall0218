package com.atguigu.gmall0218.service;

import com.atguigu.gmall0218.bean.*;

import java.util.List;

/**
 * @Auther: hftang
 * @Date: 2019/12/12 19:29
 * @Description:
 */
public interface ManageService {

    public List<BaseCatalog1> getCatalog1();

    public List<BaseCatalog2> getCatalog2(String catalog1Id);

    public List<BaseCatalog3> getCatalog3(String catalog2Id);

    public List<BaseAttrInfo> getAttrList(String catalog3Id);

    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    BaseAttrInfo getAttrValueList(String attrId);

    List<SpuInfo> getSpuInfoList(SpuInfo spuInfo);

    //获取商品售卖的基本属性
    List<BaseSaleAttr> getBaseSaleAttrList();

    //保存spuinfo
    void saveSpuInfo(SpuInfo spuInfo);

    List getSpuImageList(SpuImage spuImage);

    List<SpuSaleAttr> getSpuSaleAttrList(String spuId);

    void saveSkuInfo(SkuInfo skuInfo);
}
