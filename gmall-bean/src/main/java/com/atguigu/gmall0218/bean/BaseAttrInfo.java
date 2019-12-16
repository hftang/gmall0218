package com.atguigu.gmall0218.bean;

import javafx.stage.Stage;
import lombok.Data;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Auther: hftang
 * @Date: 2019/12/12 19:24
 * @Description:
 */
@Data
public class BaseAttrInfo implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private String id;
    @Column
    private String attrName;
    @Column
    private String catalog3Id;

    //属性值
    @Transient
    private List<BaseAttrValue> attrValueList;

}
