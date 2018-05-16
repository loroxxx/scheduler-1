package com.jinhui.scheduler.web.api.controller.preparing;

import com.jinhui.scheduler.data.core.query.preparing.PreparingQueryManagement;
import com.jinhui.scheduler.data.core.query.preparing.criteria.*;
import com.jinhui.scheduler.data.core.query.preparing.entity.*;
import com.jinhui.scheduler.web.api.vo.base.BasePageVo;
import com.jinhui.scheduler.web.api.vo.base.WebConstants;
import com.jinhui.scheduler.web.api.vo.base.WebPageResult;
import com.jinhui.scheduler.web.api.vo.preparing.criteria.*;
import com.jinhui.scheduler.web.api.vo.preparing.entity.*;
import com.jinhui.scheduler.web.api.vo.preparing.result.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/preparing")
@Api(value = "preparing", description = "试算相关接口")
public class PreparingApiController {
    private final static Logger logger = LoggerFactory.getLogger(PreparingApiController.class);

    @Autowired
    private PreparingQueryManagement preparingQuery;

    @ApiOperation(value = "查询客户试算信息", response = TradingResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findTrading", method = POST)
    public TradingResult findTrading(@ApiParam(value = "交易试算", required = true) @RequestBody TradingCriteriaVo criteriaVo){
        if(logger.isInfoEnabled()) {
            logger.info("查询试算 交易: {}",criteriaVo.toString());
        }
        TradingResult result = new TradingResult();
        if(!pageVoCheck(criteriaVo, result))
            return result;

        TradingCriteria criteria = new TradingCriteria();
        BeanUtils.copyProperties(criteriaVo, criteria);
        criteria.setSize(criteriaVo.getPageSize());
        criteria.setOffset(criteriaVo.getOffset());
        Page<TradingEntity> entityPage = preparingQuery.findTradingWithPage(criteria);
        result.setCurrentPage(entityPage.getNumber());
        result.setTotalPage(entityPage.getTotalPages());
        result.setPageSize(entityPage.getSize());
        result.setTotalSize((int)entityPage.getTotalElements());
        List<TradingEntityVo> entityVos = new ArrayList<>((int)entityPage.getTotalElements());
        for(TradingEntity entity:entityPage.getContent()){
            TradingEntityVo entityVo = new TradingEntityVo();
            BeanUtils.copyProperties(entity, entityVo);
            entityVos.add(entityVo);
        }
        result.setVoList(entityVos);
        result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
        if(logger.isInfoEnabled()) {
            logger.info("查询交易试算试算返回: size = {}", result.getVoList().size());
        }

        return result;
    }

    @ApiOperation(value = "查询持仓试算信息", response = PositionResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findPosition", method = POST)
    public PositionResult findPosition(@ApiParam(value = "持仓试算", required = true) @RequestBody PositionCriteriaVo criteriaVo){
        if(logger.isInfoEnabled()) {
            logger.info("查询持仓试算: {}",criteriaVo.toString());
        }
        PositionResult result = new PositionResult();
        if(!pageVoCheck(criteriaVo, result))
            return result;

        PositionCriteria criteria = new PositionCriteria();
        BeanUtils.copyProperties(criteriaVo, criteria);
        criteria.setSize(criteriaVo.getPageSize());
        criteria.setOffset(criteriaVo.getOffset());
        Page<PositionEntity> entityPage = preparingQuery.findPositionWithPage(criteria);
        result.setCurrentPage(entityPage.getNumber());
        result.setTotalPage(entityPage.getTotalPages());
        result.setPageSize(entityPage.getSize());
        List<PositionEntityVo> entityVos = new ArrayList<>((int)entityPage.getTotalElements());
        for(PositionEntity entity:entityPage.getContent()){
            PositionEntityVo entityVo = new PositionEntityVo();
            BeanUtils.copyProperties(entity, entityVo);
            entityVos.add(entityVo);
        }
        result.setVoList(entityVos);
        result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
        if(logger.isInfoEnabled()) {
            logger.info("查询持仓试算返回: size = {}", result.getVoList().size());
        }

        return result;
    }

    @ApiOperation(value = "查询产品试算信息", response = ProductResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findProduct", method = POST)
    public ProductResult findProduct(@ApiParam(value = "产品试算") @RequestBody ProductCriteriaVo criteriaVo){
        if(logger.isInfoEnabled()) {
            logger.info("查询产品试算: {}",criteriaVo.toString());
        }
        ProductResult result = new ProductResult();

        ProductCriteria criteria = new ProductCriteria();
        BeanUtils.copyProperties(criteriaVo, criteria);
        List<ProductEntity> entities = preparingQuery.findProduct(criteria);
        List<ProductEntityVo> entityVos = new ArrayList<>(entities.size());
        for(ProductEntity entity:entities){
            ProductEntityVo entityVo = new ProductEntityVo();
            BeanUtils.copyProperties(entity, entityVo);
            entityVos.add(entityVo);
        }
        result.setVoList(entityVos);
        result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
        if(logger.isInfoEnabled()) {
            logger.info("查询产品试算返回: size = {}", result.getVoList().size());
        }

        return result;
    }

    @ApiOperation(value = "查询交易所试算信息", response = TAResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findTA", method = POST)
    public TAResult findTA(@ApiParam(value = "交易所试算") @RequestBody TACriteriaVo criteriaVo){
        if(logger.isInfoEnabled()) {
            logger.info("查询交易所试算: {}",criteriaVo.toString());
        }
        TAResult result = new TAResult();

        TACriteria criteria = new TACriteria();
        BeanUtils.copyProperties(criteriaVo, criteria);
        List<TAEntity> entities = preparingQuery.findTA(criteria);
        List<TAEntityVo> entityVos = new ArrayList<>(entities.size());
        for(TAEntity entity:entities){
            TAEntityVo entityVo = new TAEntityVo();
            BeanUtils.copyProperties(entity, entityVo);
            entityVos.add(entityVo);
        }
        result.setVoList(entityVos);
        result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
        if(logger.isInfoEnabled()) {
            logger.info("查询交易所试算返回: size = {}", result.getVoList().size());
        }

        return result;
    }

    @ApiOperation(value = "查询渠道试算信息", response = ChannelResult.class, httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "/findChannel", method = POST)
    public ChannelResult findChannel(@ApiParam(value = "渠道试算") @RequestBody ChannelCriteriaVo criteriaVo){
        if(logger.isInfoEnabled()) {
            logger.info("查询渠道试算: {}",criteriaVo.toString());
        }
        ChannelResult result = new ChannelResult();

        ChannelCriteria criteria = new ChannelCriteria();
        BeanUtils.copyProperties(criteriaVo, criteria);
        List<ChannelEntity> entities = preparingQuery.findChannel(criteria);
        List<ChannelEntityVo> entityVos = new ArrayList<>(entities.size());
        for(ChannelEntity entity:entities){
            ChannelEntityVo entityVo = new ChannelEntityVo();
            BeanUtils.copyProperties(entity, entityVo);
            entityVos.add(entityVo);
        }
        result.setVoList(entityVos);
        result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
        if(logger.isInfoEnabled()) {
            logger.info("查询交易所试算返回: size = {}", result.getVoList().size());
        }

        return result;
    }

    /**
     * 分页请求参数检查
     * @param pageVo
     * @param result
     * @return false:参数错误
     */
    private static boolean pageVoCheck(BasePageVo pageVo, WebPageResult result){
        if(pageVo.getCurrentPage() < 0){
            result.setResultCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage("页码错误");
            return false;
        } else if (pageVo.getPageSize() < 1){
            result.setResultCode(WebConstants.RESULT_FAIL_CODE);
            result.setMessage("每页大小不能小于1");
            return false;
        }
        return true;
    }

}
