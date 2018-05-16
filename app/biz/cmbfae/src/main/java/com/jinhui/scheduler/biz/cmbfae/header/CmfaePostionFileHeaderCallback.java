package com.jinhui.scheduler.biz.cmbfae.header;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jinhui.scheduler.biz.cmbfae.service.position.PositionService;
import com.jinhui.scheduler.biz.cmbfae.util.CommonUtil;
import com.jinhui.scheduler.domain.cmbfae.template.JsonTemplate;
import com.jinhui.scheduler.domain.cmbfae.template.PositionSuContent;
import com.jinhui.scheduler.domain.cmbfae.template.ProfitContent;
import org.apache.commons.io.IOUtils;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by Administrator on 2018/1/17 0017.
 */
public class CmfaePostionFileHeaderCallback implements FlatFileHeaderCallback {


    @Autowired
    private PositionService positionService;


    @Override
    public void writeHeader(Writer writer) throws IOException {

        List<PositionSuContent> suContents = positionService.querySummarize();

        for (int i = 0; i <suContents.size() ; i++) {

            JsonTemplate<PositionSuContent> temp = new JsonTemplate<>(suContents.get(i), "summarize");
            String dString = JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue);
            writer.write(dString);
            if (i<suContents.size()-1){
                writer.write(CommonUtil.getLine());
            }
        }
    }
}
