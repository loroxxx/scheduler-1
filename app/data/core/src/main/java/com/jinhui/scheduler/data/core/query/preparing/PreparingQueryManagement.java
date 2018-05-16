package com.jinhui.scheduler.data.core.query.preparing;


import com.jinhui.scheduler.data.core.mapper.preparing.PreparingMapper;
import com.jinhui.scheduler.data.core.query.preparing.criteria.*;
import com.jinhui.scheduler.data.core.query.preparing.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jinhui on 2017/6/1.
 */
@Service
public class PreparingQueryManagement {

    @Autowired
    private PreparingMapper preparingMapper;

    public Page<TradingEntity> findTradingWithPage(TradingCriteria criteria){
        if(StringUtils.isEmpty(criteria.getBatchDate())){
            //设置默认批次日期
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            criteria.setBatchDate(date);
        }
        int total = preparingMapper.countTrading(criteria);
        List<TradingEntity> entities = Collections.emptyList();
        if(total > 0) {
            entities = preparingMapper.findTrading(criteria);
        }
        Page<TradingEntity> entityPage = new PageImpl<>(entities,
                new PageRequest(criteria.getCurrentPage(), criteria.getSize()), total);
        return entityPage;
    }

    public Page<PositionEntity> findPositionWithPage(PositionCriteria criteria){
        if(StringUtils.isEmpty(criteria.getBatchDate())){
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            criteria.setBatchDate(date);
        }
        int total = preparingMapper.countPosition(criteria);
        List<PositionEntity> entities = Collections.emptyList();
        if(total > 0) {
            entities = preparingMapper.findPosition(criteria);
        }
        Page<PositionEntity> entityPage = new PageImpl<>(entities,
                new PageRequest(criteria.getCurrentPage(), criteria.getSize()), total);
        return entityPage;
    }

    public List<ProductEntity> findProduct(ProductCriteria criteria){
        if(StringUtils.isEmpty(criteria.getBatchDate())){
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            criteria.setBatchDate(date);
        }
        List<ProductEntity> entities = preparingMapper.findProductTScale(criteria);
        List<ProductEntity> productHistScale = preparingMapper.findProductHistoryScale(criteria);
        /**
         * 合并
         */
        Map<String, ProductEntity> histScaleMap = new HashMap<>();
        for(ProductEntity histScale:productHistScale){
            histScaleMap.put(histScale.getProductNo(), histScale);
        }
        //计入T日
        for(ProductEntity entity:entities){
            ProductEntity histScale = histScaleMap.remove(entity.getProductNo());
            if(histScale != null){
                entity.applyHistScale(histScale.getScale());
            } else {
                entity.applyHistScale(BigDecimal.ZERO);
            }
        }
        //加入T日未发生交易记录产品
        for(Map.Entry<String, ProductEntity> entityEntry:histScaleMap.entrySet()){
            entities.add(entityEntry.getValue());
        }
        return entities;
    }

    public List<TAEntity> findTA(TACriteria criteria){
        if(StringUtils.isEmpty(criteria.getBatchDate())){
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            criteria.setBatchDate(date);
        }
        List<TAEntity> entities = preparingMapper.findTATissueScale(criteria);
        List<TAEntity> taHistScale = preparingMapper.findTAHistIssueScale(criteria);
        /**
         * 合并
         */
        //计入T日
        Map<String, TAEntity> histScaleMap = new HashMap<>();
        for(TAEntity histScale:taHistScale){
            histScaleMap.put(histScale.getTaCode(), histScale);
        }
        for(TAEntity entity:entities){
            TAEntity histScale = histScaleMap.remove(entity.getTaCode());
            if(histScale != null){
                entity.applyHistIssueScale(histScale.getIssueScale());
            } else {
                entity.applyHistIssueScale(BigDecimal.ZERO);
            }
        }
        //加入T日未发生交易记录TA
        for(Map.Entry<String, TAEntity> entityEntry:histScaleMap.entrySet()){
            entities.add(entityEntry.getValue());
        }
        return entities;
    }

    public List<ChannelEntity> findChannel(ChannelCriteria criteria){
        if(StringUtils.isEmpty(criteria.getBatchDate())){
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            criteria.setBatchDate(date);
        }
        List<ChannelEntity> entities = preparingMapper.findChannelTSellScale(criteria);
        List<ChannelEntity> channelHistScale = preparingMapper.findChannelHistSellScale(criteria);
        /**
         * 合并
         */
        //计入T日
        Map<String, ChannelEntity> histScaleMap = new HashMap<>();
        for(ChannelEntity histScale:channelHistScale){
            histScaleMap.put(histScale.getChannelCode(), histScale);
        }
        for(ChannelEntity entity:entities){
            ChannelEntity histScale = histScaleMap.remove(entity.getChannelCode());
            if(histScale != null){
                entity.applyHistIssueScale(histScale.getSellScale());
            }
        }
        //加入T日未发生交易记录TA
        for(Map.Entry<String, ChannelEntity> entityEntry:histScaleMap.entrySet()){
            entities.add(entityEntry.getValue());
        }

        return entities;
    }
}
