package com.jinhui.scheduler.biz.core.product.service;

import com.jinhui.scheduler.domain.core.ChildProductVo;

import java.util.List;

/**
 * 子产品分组服务
 * Created by Administrator on 2017/12/25 0025.
 */
public interface ProductDividedService {


    /**
     * 给产品添加子产品分组
     */
    public void addChild(ChildProductVo childProductVo);


    /**
     * 查询子产品分组信息
     */
    public List<ChildProductVo> queryChildProducts(String productNo, String exchangeNo);

}
