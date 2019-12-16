package com.atguigu.gmall.manage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.manage.mapper.*;
import com.atguigu.gmall0218.bean.*;
import com.atguigu.gmall0218.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: hftang
 * @Date: 2019/12/12 19:30
 * @Description:
 */

@Service
@Component
public class ManageServiceImpl implements ManageService {
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;

    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;

    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;
    @Autowired
    private SpuInfoMapper spuInfoMapper;
    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;
    @Autowired
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    //
    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuImageMapper skuImageMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;


    @Override
    public List<BaseCatalog1> getCatalog1() {
        return this.baseCatalog1Mapper.selectAll();
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {

        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        List<BaseCatalog2> baseCatalog2List = this.baseCatalog2Mapper.select(baseCatalog2);

        return baseCatalog2List;
    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);
        List<BaseCatalog3> catalog3s = this.baseCatalog3Mapper.select(baseCatalog3);
        return catalog3s;
    }

    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {

//        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
//        baseAttrInfo.setCatalog3Id(catalog3Id);
//        List<BaseAttrInfo> baseAttrInfos = this.baseAttrInfoMapper.select(baseAttrInfo);

        List<BaseAttrInfo> baseAttrInfos = this.baseAttrInfoMapper.getBaseAttrInfoListByCatalog3Id(catalog3Id);

        return baseAttrInfos;
    }

    @Override
    @Transactional
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {

        //1先保存属性的名称

        if (baseAttrInfo.getId() != null || baseAttrInfo.getId().length() > 0) {
            //修改操作
            this.baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        } else {
            //插入操作
            this.baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }

        //2保存属性的值


        //先删除 再保存
        BaseAttrValue record = new BaseAttrValue();
        record.setAttrId(baseAttrInfo.getId());
        this.baseAttrValueMapper.delete(record);


        for (BaseAttrValue baseAttrValue : baseAttrInfo.getAttrValueList()) {

            baseAttrValue.setAttrId(baseAttrInfo.getId());

            this.baseAttrValueMapper.insert(baseAttrValue);

        }
    }

//    @Override
//    public List<BaseAttrValue> getAttrValueList(String attrId) {
//        BaseAttrValue record = new BaseAttrValue();
//        record.setAttrId(attrId);
//        return this.baseAttrValueMapper.select(record);
//    }


    @Override
    public BaseAttrInfo getAttrValueList(String attrId) {

        BaseAttrInfo baseAttrInfo = this.baseAttrInfoMapper.selectByPrimaryKey(attrId);

        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(baseAttrInfo.getId());

        List<BaseAttrValue> baseAttrValues = this.baseAttrValueMapper.select(baseAttrValue);

        baseAttrInfo.setAttrValueList(baseAttrValues);

        return baseAttrInfo;
    }

    @Override
    public List<SpuInfo> getSpuInfoList(SpuInfo spuInfo) {
        return this.spuInfoMapper.select(spuInfo);
    }

    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        List<BaseSaleAttr> saleAttrList = this.baseSaleAttrMapper.selectAll();
        return saleAttrList;
    }

    //保存spuinfo
    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {

        //1
        this.spuInfoMapper.insertSelective(spuInfo);


        //2
        for (SpuImage spuImage : spuInfo.getSpuImageList()) {
            spuImage.setSpuId(spuInfo.getId());
            this.spuImageMapper.insertSelective(spuImage);
        }

        for (SpuSaleAttr spuSaleAttr : spuInfo.getSpuSaleAttrList()) {
            spuSaleAttr.setSpuId(spuInfo.getId());
            this.spuSaleAttrMapper.insertSelective(spuSaleAttr);

            for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttr.getSpuSaleAttrValueList()) {
                spuSaleAttrValue.setSpuId(spuInfo.getId());
                this.spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
            }
        }


    }


    @Override
    public List getSpuImageList(SpuImage spuImage) {
        SpuImage record = new SpuImage();
        record.setSpuId(spuImage.getSpuId());
        List<SpuImage> spuImageList = this.spuImageMapper.select(record);
        return spuImageList;
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(String spuId) {
        List<SpuSaleAttr> spuSaleAttrList = this.spuSaleAttrMapper.getSpuSaleAttrList(spuId);
        return spuSaleAttrList;
    }

    //保存skuinfo
    @Override
    @Transactional
    public void saveSkuInfo(SkuInfo skuInfo) {

        /**
         * skuinfo
         * skuimage
         * skusaleattr
         * skusaleattrvalue
         */

        this.skuInfoMapper.insertSelective(skuInfo);
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        if (skuImageList != null && skuImageList.size() > 0) {
            for (SkuImage skuImage : skuImageList) {
                skuImage.setSkuId(skuInfo.getId());
                this.skuImageMapper.insertSelective(skuImage);
            }
        }

        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        if (skuAttrValueList != null && skuAttrValueList.size() > 0) {
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(skuInfo.getId());
                this.skuAttrValueMapper.insertSelective(skuAttrValue);
            }
        }

        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        if (skuSaleAttrValueList != null && skuSaleAttrValueList.size() > 0) {
            for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
                skuSaleAttrValue.setSkuId(skuInfo.getId());
                if(skuSaleAttrValue.getSaleAttrValueId()==null){
                    skuSaleAttrValue.setSaleAttrValueId("77");
                }

                this.skuSaleAttrValueMapper.insertSelective(skuSaleAttrValue);
            }
        }


    }

}
