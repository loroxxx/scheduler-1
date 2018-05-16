package com.jinhui.scheduler.web.api.vo.preparing.result;

import com.jinhui.scheduler.web.api.vo.base.WebPageResult;
import com.jinhui.scheduler.web.api.vo.preparing.entity.ChannelEntityVo;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by jinhui on 2017/6/1.
 */
public class ChannelResult extends WebPageResult{

    @ApiModelProperty(value = "结果集", dataType = "com.jinhui.scheduler.web.api.vo.preparing.ChannelEntityVo")
    private List<ChannelEntityVo> voList;

    public List<ChannelEntityVo> getVoList() {
        return voList;
    }

    public void setVoList(List<ChannelEntityVo> voList) {
        this.voList = voList;
    }

    @Override
    public String toString() {
        return "ChannelResult{" +
                "voList=" + voList +
                "}," + super.toString();
    }
}
