package com.jinhui.scheduler.biz.gzefe.processor;

import com.jinhui.scheduler.biz.gzefe.pojo.AccountApplyInfo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

/**
 * Created by wsc on 2017/5/21.
 */
public class GzefeAccountApplyDBProcessor implements ItemProcessor<AccountApplyInfo, AccountApplyInfo> {

    @Override
    public AccountApplyInfo process(AccountApplyInfo accountApplyInfoIn) throws Exception {
        AccountApplyInfo accountApplyInfoOut = new AccountApplyInfo();

        BeanUtils.copyProperties(accountApplyInfoIn, accountApplyInfoOut);

        return accountApplyInfoOut;
    }
}

