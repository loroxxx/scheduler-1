package com.jinhui.scheduler.data.core.mapper.preparing;

import com.jinhui.scheduler.data.core.query.preparing.criteria.*;
import com.jinhui.scheduler.data.core.query.preparing.entity.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jinhui on 2017/6/1.
 */
public interface PreparingMapper {

    List<TradingEntity> findTrading(TradingCriteria criteria);
    int countTrading(TradingCriteria criteria);

    List<PositionEntity> findPosition(PositionCriteria criteria);
    int countPosition(PositionCriteria criteria);

    List<ProductEntity> findProductTScale(ProductCriteria criteria);
    List<ProductEntity> findProductHistoryScale(ProductCriteria criteria);

    List<TAEntity> findTATissueScale(TACriteria criteria);
    List<TAEntity> findTAHistIssueScale(TACriteria criteria);

    List<ChannelEntity> findChannelTSellScale(ChannelCriteria criteria);
    List<ChannelEntity> findChannelHistSellScale(ChannelCriteria criteria);
}
