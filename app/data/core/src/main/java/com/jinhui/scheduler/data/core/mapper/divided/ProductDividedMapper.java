package com.jinhui.scheduler.data.core.mapper.divided;

import com.jinhui.scheduler.domain.divided.ProductDivided;

/**
 * Created by jinhui on 2017/5/27.
 */
public interface       ProductDividedMapper {

    ProductDivided findByCriteria(ProductDivided.DividedCriteria criteria);

    void updateCount(ProductDivided divided);

    void save(ProductDivided divided);

}
