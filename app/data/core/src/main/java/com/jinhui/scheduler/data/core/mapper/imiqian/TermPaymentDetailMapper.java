package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.TermPaymentDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TermPaymentDetailMapper {

    int insertDetail(TermPaymentDetail record);

    int updateByPrimaryKeySelective(TermPaymentDetail record);

    /**
     * 查询当天是否有付息的记录
     * @return
     */
    List<TermPaymentDetail> selectToPayInterest();

    /**
     * 查询当天是否有付息的记录
     * @return
     */
    List<TermPaymentDetail> selectToPayInterestWithChoice(@Param("preWorkDay") String preWorkDay);

    /**
     * 更新客户回款状态为“已付息”
     * @param id
     * @return
     */
    int updateToPayInterest(Integer id);
}