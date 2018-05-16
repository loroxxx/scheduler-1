package com.jinhui.scheduler.web.api.controller.paramSetting;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.core.chnInfo.ChnInfoService;
import com.jinhui.scheduler.biz.core.exception.BizException;
import com.jinhui.scheduler.biz.core.exchange.ExchangeInfoService;
import com.jinhui.scheduler.biz.core.product.service.ChnProductService;
import com.jinhui.scheduler.data.core.mapper.core.ProductDao;
import com.jinhui.scheduler.domain.core.ChnInfo;
import com.jinhui.scheduler.domain.core.ChnInfoProductVo;
import com.jinhui.scheduler.web.api.vo.base.SimpleWebResult;
import com.jinhui.scheduler.web.api.vo.base.WebConstants;
import com.jinhui.scheduler.web.api.vo.preparing.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinhui.scheduler.domain.cmbfae.model.ChnProduct;

@Controller
@RequestMapping("/chnInfo")
public class ChnInfoController {
    private final static Logger logger = LoggerFactory.getLogger(ChnInfoController.class);

    private final static BigDecimal tenThousand = new BigDecimal(10000);

    @Autowired
    private ChnProductService chnProductService;

    @Autowired
    private ChnInfoService chnInfoService;

    @Autowired
    private BatchStateService batchStateService;

    /**
     * 查询渠道列表
     *
     * @return
     */
    @RequestMapping(value = "/queryChnsInfo",  method = RequestMethod.GET)
    @ResponseBody
    public BaseResult queryChnList() {
        BaseResult<List> result = new BaseResult<>();

        try {
            List<ChnInfo> list = chnInfoService.queryList();
            result.setData(list);
            result.setMessage("查询成功");
            result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
            return result;

        } catch (Exception e) {
            logger.error("查询渠道列表失败", e);
            result.setMessage("查询渠道列表失败");
            result.setResultCode(WebConstants.RESULT_FAIL_CODE);
            return result;
        }


    }


    /**
     * 查询 渠道和与渠道对应的产品信息
     *
     * @return
     */
    @RequestMapping(value = "/queryChnProduct", method = RequestMethod.GET)
    @ResponseBody
    public BaseResult queryChnAndProductInfo(@RequestParam("chnCode") String chnCode) {
        BaseResult<List> result = new BaseResult<>();

        try {
            List<ChnProduct> list = chnProductService.queryChnProductInfo(chnCode);
            for (ChnProduct chnProduct : list) {
                //收益率转为百分比%
                BigDecimal incomeRate = chnProduct.getIncomeRate();
                BigDecimal multiply = incomeRate.multiply(new BigDecimal("100"));
                BigDecimal setScale = multiply.setScale(3, BigDecimal.ROUND_DOWN);
                chnProduct.setIncomeRate(setScale);
            }
            result.setData(list);
            result.setMessage("查询成功");
            result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);

            return result;
        } catch (Exception e) {
            result.setMessage("查询失败");
            result.setResultCode(WebConstants.RESULT_FAIL_CODE);
            return result;
        }

    }


    /**
     * 更新渠道总额度和渠道产品信息
     * @return
     */
    @RequestMapping(value = "/updateChnProduct", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public SimpleWebResult queryChnAndProductInfo(@RequestBody ChnInfoProductVo chnInfoVo) {

        try {
            //渠道规模限制（万元）
            BigDecimal totalLimit = chnInfoVo.getTotalLimit().multiply(tenThousand);

            List<ChnProduct> list = chnInfoVo.getList();

            Date updateDate = batchStateService.getBatchState().getDate();

            for (ChnProduct chnProduct : list) {
                //产品当日申购上限（万元）
                BigDecimal productSubsTopLimit = chnProduct.getProductSubsToplimit().multiply(tenThousand);
                //产品当日赎回上限（万元）
                BigDecimal productRedeemTopLimit = chnProduct.getProductRedeemToplimit().multiply(tenThousand);
                //产品规模限制（万元）
                BigDecimal newTotalLimit = chnProduct.getTotalLimit().multiply(tenThousand);

                chnProduct.setProductSubsToplimit(productSubsTopLimit);

                chnProduct.setProductRedeemToplimit(productRedeemTopLimit);

                chnProduct.setTotalLimit(newTotalLimit);

                chnProductService.updateChnProduct(chnProduct, updateDate);

            }
            chnInfoService.updateChnLimit(totalLimit, chnInfoVo.getChnCode());

            return SimpleWebResult.ok();

        } catch (RuntimeException e) {
            throw new BizException("提交参数失败");
        }

    }


}
