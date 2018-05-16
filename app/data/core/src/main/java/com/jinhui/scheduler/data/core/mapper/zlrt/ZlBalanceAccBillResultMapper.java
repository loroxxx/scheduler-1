package com.jinhui.scheduler.data.core.mapper.zlrt;


import com.jinhui.scheduler.domain.zlrt.ZlBalanceAccBillResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZlBalanceAccBillResultMapper {

    int insertSelective(ZlBalanceAccBillResult record);

    ZlBalanceAccBillResult selectByPrimaryKey(Integer id);

    List<ZlBalanceAccBillResult> queryListAfterBalance(@Param("batchCode") Integer batchCode);

}