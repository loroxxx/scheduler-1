package com.jinhui.scheduler.domain.core;

import com.jinhui.scheduler.domain.cmbfae.model.ChnProduct;

import java.math.BigDecimal;
import java.util.List;

/**
 * 渠道信息和渠道对应的渠道产品
 * Created by Administrator on 2018/1/12 0012.
 */
public class ChnInfoProductVo {

    private String chnCode;

    private BigDecimal totalLimit;

    private List<ChnProduct> list;

    public String getChnCode() {
        return chnCode;
    }

    public void setChnCode(String chnCode) {
        this.chnCode = chnCode;
    }

    public BigDecimal getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(BigDecimal totalLimit) {
        this.totalLimit = totalLimit;
    }

    public List<ChnProduct> getList() {
        return list;
    }

    public void setList(List<ChnProduct> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ChnInfoVo{" +
                "chnCode='" + chnCode + '\'' +
                ", totalLimit=" + totalLimit +
                ", list=" + list +
                '}';
    }
}
