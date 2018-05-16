package com.jinhui.scheduler.data.core.mapper.imiqian;


import com.jinhui.scheduler.domain.imiqian.domain.InvestorTrans;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvestorTransMapper {

    int delete(@Param("batchCode") int batchCode);

    int save(InvestorTrans investorTrans);

    int saveTransTemp(InvestorTrans investorTrans);

    InvestorTrans selectByPrimaryKey(Integer id);

    InvestorTrans selectTransByChoice(InvestorTrans investorTrans);

    int updateTrans(InvestorTrans investorTrans);

    int updateTransTemp(InvestorTrans investorTrans);

    int findListCount(InvestorTrans investorTrans);

    List<InvestorTrans> findList(InvestorTrans investorTrans);

    /**
     * 查询确认成功的交易
     * @param investorTrans
     * @return
     */
    List<InvestorTrans> findConfrimedList(InvestorTrans investorTrans);

    /**
     * 删除交易临时表数据
     */
    int deleteTransTemp();

    /**
     * 将T日交易数据移到临时表
     * @return
     */
    int transferDataToTemp(InvestorTrans investorTrans);

    /**
     * 业务汇总
     * @return
     */
    List<InvestorTrans> gatherBusiness(InvestorTrans investorTrans);

    /**
     * 查询当前批次的交易数据
     * @return
     */
    List<InvestorTrans> findTransTempList(InvestorTrans investorTrans);

    //----------------------------------------  以下为定期产品方法  ----------------------------------

    /**
     * 根据产品代码查询客户投资记录
     * @param investorTrans
     * @return
     */
    List<InvestorTrans> findRegularProductTransList(InvestorTrans investorTrans);

}