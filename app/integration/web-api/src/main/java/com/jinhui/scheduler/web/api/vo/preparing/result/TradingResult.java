package com.jinhui.scheduler.web.api.vo.preparing.result;

import com.jinhui.scheduler.web.api.vo.base.WebPageResult;
import com.jinhui.scheduler.web.api.vo.preparing.entity.TradingEntityVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by jinhui on 2017/6/1.
 */
public class TradingResult extends WebPageResult{

    @ApiModelProperty(value = "结果集", dataType = "com.jinhui.scheduler.web.api.vo.preparing.TradingEntityVo")
    private List<TradingEntityVo> voList;

    public List<TradingEntityVo> getVoList() {
        return voList;
    }

    public void setVoList(List<TradingEntityVo> voList) {
        this.voList = voList;
    }

    @Override
    public String toString() {
        return "TradingResult{" +
                "voList=" + voList +
                "}," + super.toString();
    }
}
