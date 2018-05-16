package com.jinhui.scheduler.biz.cmbfae.header;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jinhui.scheduler.biz.cmbfae.service.trans.TransService;
import com.jinhui.scheduler.biz.cmbfae.util.CommonUtil;
import com.jinhui.scheduler.domain.cmbfae.template.JsonTemplate;
import com.jinhui.scheduler.domain.cmbfae.template.PositionSuContent;
import com.jinhui.scheduler.domain.cmbfae.template.TransSuContent;
import org.apache.commons.io.IOUtils;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/16 0016.
 */
public class CmfaeTransApplyFileHeaderCallback implements FlatFileHeaderCallback {


    @Autowired
    private TransService transService;


    @Override
    public void writeHeader(Writer writer) throws IOException {

        List<TransSuContent> list = transService.querySummarize();

        for (int i = 0; i < list.size(); i++) {

            JsonTemplate<TransSuContent> temp = new JsonTemplate<>(list.get(i), "summarize");
            String s = JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue);
            writer.write(s);
            if (i < list.size() - 1) {
                writer.write(CommonUtil.getLine());
            }
        }

    }


}
