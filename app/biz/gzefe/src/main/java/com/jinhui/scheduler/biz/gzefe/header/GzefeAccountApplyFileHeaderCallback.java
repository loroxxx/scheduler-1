package com.jinhui.scheduler.biz.gzefe.header;


import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.gzefe.constant.GzefeFileConstants;
import com.jinhui.scheduler.data.core.mapper.imiqian.ChnOpenMapper;
import com.jinhui.scheduler.domain.cmbfae.model.BatchState;
import com.jinhui.scheduler.domain.common.ChannelCode;
import com.jinhui.scheduler.domain.imiqian.domain.ChnOpen;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Date;

/**
 * Created by wsc on 2017/05/21
 */
public class GzefeAccountApplyFileHeaderCallback implements FlatFileHeaderCallback {
    private final static Logger logger = LoggerFactory.getLogger(GzefeAccountApplyFileHeaderCallback.class);

    @Autowired
    JobExplorerFactoryBean jobExplorer;

    @Autowired
    private ChnOpenMapper chnOpenMapper;

    @Autowired
    private BatchStateService batchStateService;

    @Override
    public void writeHeader(Writer writer) throws IOException {

        /*
         * 可以从数据库中查询当前批次和当前跑批日期
         */
        BatchState batchStateCurrent = batchStateService.getBatchStateCurrent();
        Date batchDate = batchStateCurrent.getDate();
        int batchCode=batchStateCurrent.getBatchCode();
        String applyDate = new DateTime(batchDate).toString("yyyyMMdd");

        File file = ResourceUtils.getFile(GzefeFileConstants.HEADERFILEPATH_01);//账户确认文件头信息
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = "";
            while ((s = br.readLine()) != null) {
                if ("#{applyDate}".equals(s)) {
                    s = applyDate;
                }
                result.append(s + System.lineSeparator());
            }
            br.close();
            ChnOpen chnOpen = new ChnOpen();
            //TODO 测试代码要删除
            chnOpen.setBatchCode(batchCode);
            chnOpen.setChnCode(ChannelCode.Imiqian.getCode());
            int count = chnOpenMapper.findListCount(chnOpen);
            result.append(String.format("%08d", count));//记录数
        } catch (Exception e) {
            logger.info("账户申请文件生成出错了", e);
        }

        writer.write(result.toString());
    }
}
