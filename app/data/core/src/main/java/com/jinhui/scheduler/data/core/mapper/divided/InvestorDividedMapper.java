package com.jinhui.scheduler.data.core.mapper.divided;


import com.jinhui.scheduler.domain.divided.InvestorDivided;

/**
 * Created by jinhui on 2017/5/27.
 */
public interface InvestorDividedMapper {

    InvestorDivided find(InvestorDivided divided);

    void save(InvestorDivided divided);

}
