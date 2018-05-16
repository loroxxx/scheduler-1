package com.jinhui.scheduler.biz.cmbfae.process;

import com.jinhui.scheduler.biz.cmbfae.constans.IDType;
import com.jinhui.scheduler.biz.cmbfae.util.CommonUtil;
import com.jinhui.scheduler.domain.cmbfae.template.JsonTemplate;
import com.jinhui.scheduler.domain.cmbfae.template.PositionDetailContent;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Administrator on 2018/1/17 0017.
 */
public class CmfaePositionProcessor implements ItemProcessor<PositionDetailContent, JsonTemplate<PositionDetailContent>> {


    @Override
    public JsonTemplate<PositionDetailContent> process(PositionDetailContent item) throws Exception {


        // 对证件类型转换：
        String certificateType = item.getCertificateType();
        String cmbfaeCertType = IDType.transform(certificateType);
        item.setCertificateType(cmbfaeCertType);


        JsonTemplate<PositionDetailContent> dTemp = new JsonTemplate<>(item, "detail");
        return dTemp;
    }
}
