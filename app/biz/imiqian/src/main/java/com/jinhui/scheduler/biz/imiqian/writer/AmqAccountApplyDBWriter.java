package com.jinhui.scheduler.biz.imiqian.writer;

import com.jinhui.scheduler.biz.imiqian.utils.DateUtils;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.ChnOpenMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.UserMapper;
import com.jinhui.scheduler.domain.imiqian.domain.BatchState;
import com.jinhui.scheduler.domain.imiqian.domain.ChnOpen;
import com.jinhui.scheduler.domain.imiqian.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * created by wsc
 *  2017-05-19 19:16
 **/
public class AmqAccountApplyDBWriter implements ItemWriter<ChnOpen> {
    private final static Logger logger = LoggerFactory.getLogger(AmqAccountApplyDBWriter.class);

    @Autowired
    BatchStateMapper batchStateMapper;

    @Autowired
    ChnOpenMapper chnOpenMapper;

    @Autowired
    UserMapper userMapper;

    //批次号
    int batchCode;
    //汇总日期
    String gatherDate = "";


    @Override
    public void write(List<? extends ChnOpen> items) throws Exception {

        //初始化汇总日期和批次号
        BatchState batchState = batchStateMapper.findNewest();
        batchCode = batchState.getBatchCode();
        gatherDate = String.valueOf(batchState.getDate());

        for(ChnOpen chnOpen : items){
            int flag = chnOpenMapper.save(chnOpen);
            if(flag > 0){
                User user = new User();
                user.setIdType(chnOpen.getIdType());
                user.setIdNo(chnOpen.getIdNo());
                List<User> userList = userMapper.findList(user);
                if(userList.isEmpty() || userList.size() == 0){
                    BeanUtils.copyProperties(chnOpen,user);
                    user.setExchangeCode("");
                    user.setBatchCode(batchCode);
                    user.setRecordDate(gatherDate);
                    user.setCreateTime(DateUtils.getCurrentDatetime());
                    user.setUpdateTime(DateUtils.getCurrentDatetime());
                    userMapper.save(user);
                }

            }else{
                logger.info("渠道开户信息[申请单编号="+chnOpen.getAppSheetSerialNo()+"]保存失败");
            }
        }
    }
}
