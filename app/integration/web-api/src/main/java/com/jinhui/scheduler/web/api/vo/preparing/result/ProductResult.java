package com.jinhui.scheduler.web.api.vo.preparing.result;

import com.jinhui.scheduler.web.api.vo.base.WebResult;
import com.jinhui.scheduler.web.api.vo.preparing.entity.ProductEntityVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by jinhui on 2017/6/1.
 */
public class ProductResult extends WebResult{

    @ApiModelProperty(value = "结果集", dataType = "com.jinhui.scheduler.web.api.vo.preparing.ProductEntityVo")
    private List<ProductEntityVo> voList;

    public List<ProductEntityVo> getVoList() {
        return voList;
    }

    public void setVoList(List<ProductEntityVo> voList) {
        this.voList = voList;
    }

    @Override
    public String toString() {
        return "ProductResult{" +
                "voList=" + voList +
                "}," + super.toString();
    }
}
