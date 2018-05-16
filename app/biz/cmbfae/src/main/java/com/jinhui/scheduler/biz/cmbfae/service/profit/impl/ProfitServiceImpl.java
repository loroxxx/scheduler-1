package com.jinhui.scheduler.biz.cmbfae.service.profit.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jinhui.scheduler.biz.cmbfae.service.profit.ProfitService;
import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.core.exception.BizException;
import com.jinhui.scheduler.data.core.mapper.cmbfae.InvestorIncomeDao;
import com.jinhui.scheduler.domain.common.ExchangeType;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jinhui.scheduler.biz.cmbfae.service.log.LogService;
import com.jinhui.scheduler.biz.cmbfae.util.CommonUtil;
import com.jinhui.scheduler.data.core.mapper.core.ChnProductDao;
import com.jinhui.scheduler.data.core.mapper.cmbfae.InvestorPositionDao;
import com.jinhui.scheduler.domain.cmbfae.model.BatchState;
import com.jinhui.scheduler.domain.cmbfae.model.Product;
import com.jinhui.scheduler.domain.cmbfae.template.JsonTemplate;
import com.jinhui.scheduler.domain.cmbfae.template.ProfitContent;

@Service("ProfitService")
public class ProfitServiceImpl implements ProfitService {
    private Logger log = LoggerFactory.getLogger(ProfitServiceImpl.class);

    @Autowired
    private ChnProductDao chnProductDao;

    @Autowired
    private InvestorIncomeDao investorIncomeDao;


    @Autowired
    private BatchStateService batchStateService;

    private final static BigDecimal hundred = new BigDecimal("100");

    /**
     * 按批次，按招银前海的产品代码来导出产品收益文件
     */
    @Override
    public void exportFile(String path) throws IOException {

//        log.info("生成招银前海的收益文件：" + path);
        StringBuilder sb = new StringBuilder();
        FileOutputStream fos = new FileOutputStream(path);
        try {

            String exchangeNo = ExchangeType.Cmbfae.getExchangeNo();

            //按交易所查出持仓的子产品列表
            List<String> childProductNos = investorIncomeDao.queryChildProductNo(exchangeNo);

            BatchState current = batchStateService.getBatchStateCurrent();
            Date currentDate = current.getDate();
            DateTime dt = new DateTime(currentDate);

            List<BatchState> batchStates = batchStateService.getWorkBatchStates(dt.toString("yyyyMMdd"));

            //如果遇到非工作日，要导出多个批次的收益率
            for (BatchState batch : batchStates) {

                //从渠道产品表查询每种产品的收益利率
                for (String childProductNo : childProductNos) {
                    ProfitContent content = chnProductDao.queryProfitContont(childProductNo);

                    //收益率要转换
                    BigDecimal profitRate = content.getProfitValue().multiply(hundred);
                    content.setProfitValue(profitRate);

                    content.setProfitDate(batch.getDate());
                    JsonTemplate<ProfitContent> temp = new JsonTemplate<>(content, "detail");
                    String string = JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue);
                    sb.append(string).append(CommonUtil.getLine());

                }

            }

            IOUtils.write(sb.toString(), fos, "UTF-8");

        } catch (IOException e) {
            throw new BizException("输出招银前海的收益json文件出错:" + path);
        } finally {
            IOUtils.closeQuietly(fos);
        }

    }


}
