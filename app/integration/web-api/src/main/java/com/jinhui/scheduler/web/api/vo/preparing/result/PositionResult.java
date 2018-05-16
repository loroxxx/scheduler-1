package com.jinhui.scheduler.web.api.vo.preparing.result;

import com.jinhui.scheduler.web.api.vo.base.WebPageResult;
import com.jinhui.scheduler.web.api.vo.preparing.entity.PositionEntityVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by jinhui on 2017/6/1.
 */
public class PositionResult extends WebPageResult{

    @ApiModelProperty(value = "结果集", dataType = "com.jinhui.scheduler.web.api.vo.preparing.PositionEntityVo")
    private List<PositionEntityVo> voList;

    public List<PositionEntityVo> getVoList() {
        return voList;
    }

    public void setVoList(List<PositionEntityVo> voList) {
        this.voList = voList;
    }

    @Override
    public String toString() {
        return "PositionResult{" +
                "voList=" + voList +
                "}," + super.toString();
    }
}
