package com.jinhui.scheduler.web.api.vo.preparing.result;

import com.jinhui.scheduler.web.api.vo.base.WebPageResult;
import com.jinhui.scheduler.web.api.vo.preparing.entity.TAEntityVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by jinhui on 2017/6/1.
 */
public class TAResult extends WebPageResult{

    @ApiModelProperty(value = "结果集", dataType = "com.jinhui.scheduler.web.api.vo.preparing.TAEntityVo")
    private List<TAEntityVo> voList;

    public List<TAEntityVo> getVoList() {
        return voList;
    }

    public void setVoList(List<TAEntityVo> voList) {
        this.voList = voList;
    }

    @Override
    public String toString() {
        return "TAResult{" +
                "voList=" + voList +
                "}," + super.toString();
    }
}
