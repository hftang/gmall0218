package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall0218.bean.*;
import com.atguigu.gmall0218.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: hftang
 * @Date: 2019/12/12 18:23
 * @Description:
 */
@CrossOrigin
@Controller
public class ManageController {

    @Reference
    private ManageService manageService;

    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<BaseCatalog1> getCatelog1() {

        List<BaseCatalog1> catalog1List = this.manageService.getCatalog1();

        return catalog1List;
    }


    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<BaseCatalog2> getCatelog2(String catalog1Id) {

        List<BaseCatalog2> catalog2List = this.manageService.getCatalog2(catalog1Id);

        return catalog2List;
    }


    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<BaseCatalog3> getCatelog3(String catalog2Id) {

        List<BaseCatalog3> catalog3List = this.manageService.getCatalog3(catalog2Id);

        return catalog3List;
    }

    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<BaseAttrInfo> attrInfoList(String catalog3Id) {
        List<BaseAttrInfo> attrList = this.manageService.getAttrList(catalog3Id);
        return attrList;
    }

    //保存平台属性
    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public void saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo) {

        this.manageService.saveAttrInfo(baseAttrInfo);


    }

    //修改属性的回显

    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<BaseAttrValue> getAttrValueList(String attrId) {
        BaseAttrInfo attrValueList = this.manageService.getAttrValueList(attrId);

        return attrValueList.getAttrValueList();
    }

    //获取商品销售属性
    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        return this.manageService.getBaseSaleAttrList();
    }




}
