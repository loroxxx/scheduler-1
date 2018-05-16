package com.jinhui.scheduler.data.core.mapper.cmbfae;

import com.jinhui.scheduler.domain.cmbfae.ExchangeFileLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface ExchangeFileLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ExchangeFileLog record);

    int insertSelective(ExchangeFileLog record);

    ExchangeFileLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExchangeFileLog record);


    /**
     * 查询招银在跑批日当日有没有产品文件的导入记录
     *
     * @return
     */
    int queryProductFileCount(@Param("batchDate") Date batchDate, @Param("exchangeCode") String exchangeCode);


    /**
     * @return
     */
    ExchangeFileLog queryStepByCode(@Param("step") String step, @Param("batchDate") Date batchDate, @Param("exchangeCode") String exchangeCode);


    /**
     * 查询日志到了第几步
     */
    ExchangeFileLog queryStep(@Param("batchDate") Date batchDate, @Param("exchangeCode") String exchangeCode);


    /**
     * Step1，Step2，Step3的总数
     */
    int queryStepCount(@Param("batchDate") Date batchDate, @Param("exchangeCode") String exchangeCode);

}