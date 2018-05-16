package com.jinhui.scheduler.biz.cmbfae.process;


import com.jinhui.scheduler.biz.cmbfae.constans.IDType;
import com.jinhui.scheduler.biz.cmbfae.util.CommonUtil;
import com.jinhui.scheduler.domain.cmbfae.template.JsonTemplate;
import com.jinhui.scheduler.domain.cmbfae.template.TransDetailContent;
import org.springframework.batch.item.ItemProcessor;

/** 对交易信息做一些转换处理
 * Created by Administrator on 2018/1/16 0016.
 */
public class CmfaeTransApplyDBProcessor implements ItemProcessor<TransDetailContent, JsonTemplate<TransDetailContent>> {

    @Override
    public JsonTemplate<TransDetailContent> process(TransDetailContent item) throws Exception {


        // 对交易类型转换
        if (item.getTransactionType().equals("022")) {
            item.setTransactionType("0");
        } else if (item.getTransactionType().equals("024")) {
            item.setTransactionType("1");
        }

        // 对证件类型转换：
        String certificateType = item.getCertificateType();
        String cmbfaeCertType = IDType.transform(certificateType);
        item.setCertificateType(cmbfaeCertType);

        JsonTemplate<TransDetailContent> dTemp = new JsonTemplate<>(item, "detail");

        return dTemp;
    }
}
