package com.jinhui.scheduler.biz.cmbfae.service.product.divided;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jinhui.scheduler.biz.cmbfae.constans.CmbfaeExportSteps;
import com.jinhui.scheduler.biz.cmbfae.service.log.LogService;
import com.jinhui.scheduler.biz.cmbfae.util.CommonUtil;
import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.core.common.IFileDirectoryService;
import com.jinhui.scheduler.biz.core.common.IFileNameService;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.data.core.mapper.cmbfae.CmbfaeProductDao;
import com.jinhui.scheduler.data.core.mapper.cmbfae.ScheduleProductDividedDao;
import com.jinhui.scheduler.domain.cmbfae.model.BatchState;
import com.jinhui.scheduler.domain.cmbfae.template.JsonTemplate;
import com.jinhui.scheduler.domain.cmbfae.template.ProductContentVo;
import com.jinhui.scheduler.domain.common.ExchangeType;
import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jinhui on 2017/6/30.
 */
@Service("ProductDividedService")
public class ProductDividedServiceImpl implements ProductDividedService {
    private Logger log = LoggerFactory.getLogger(ProductDividedServiceImpl.class);

    @Autowired
    private CmbfaeProductDao productDao;


    @Autowired
    private IFileDirectoryService FileDirectoryService;


    @Autowired
    private IFileNameService iFileNameService;

    @Autowired
    private IFileService IFileService;

    @Autowired
    private LogService logService;

    @Autowired
    private ScheduleProductDividedDao scheduleProductDividedDao;


    @Autowired
    private BatchStateService batchStateService;


    private final static BigDecimal hundred = new BigDecimal("100");


    @Override
    public void exportChildProductFile(String productNo) throws IOException {

        BatchState batchStateCurrent = batchStateService.getBatchStateCurrent();


        // 获取上传目录
        String fileDir = FileDirectoryService.getWorkspaceUploadFileDir(InstitutionType.Cmbfae);

        String productName = CommonUtil.getJsonFileName("product");
        String productFileName = iFileNameService.getFileName(InstitutionType.Cmbfae,
                productName + "_(\\d{8})_(\\d{3}).req.txt", batchStateCurrent.getDate());
        String productPath = fileDir + File.separator + productFileName;


        // 查询子产品信息
        List<ProductContentVo> temps = productDao.select2JsonTemplate(productNo);


        //给招银的子产品信息中，要对收益率转换
        Iterator<ProductContentVo> iterator = temps.iterator();
        while (iterator.hasNext()) {
            ProductContentVo content = iterator.next();
            BigDecimal rate = new BigDecimal(content.getExpectedYield());
            content.setExpectedYield(rate.multiply(hundred).toString());
        }

        StringBuilder sb = new StringBuilder();
        for (ProductContentVo contentVo : temps) {
            // 组装json输出模板
            JsonTemplate<ProductContentVo> temp = new JsonTemplate<>(contentVo, "detail");
            String detail = JSON.toJSONString(temp, SerializerFeature.WriteMapNullValue);
            sb.append(detail).append(CommonUtil.getLine());
        }

        log.info("生成招银前海的子产品文件：" + productPath);
        FileOutputStream fos = new FileOutputStream(productPath);
        try {
            IOUtils.write(sb.toString(), fos, "UTF-8");

        } catch (IOException e) {
            log.error("输出招银前海的子产品文件出错：" + productPath);
            throw new IOException(e);
        } finally {
            IOUtils.closeQuietly(fos);
        }

        logService.writeProductLog(CmbfaeExportSteps.STEP_01, productPath);

        uploadFile(productPath);

        logService.writeProductLog(CmbfaeExportSteps.STEP_02, "");
    }

    @Override
    public void confirmChildProductFile() throws Exception {


        BatchState current = batchStateService.getBatchStateCurrent();

        String exchangeCode = ExchangeType.Cmbfae.getExchangeNo() + "_PRODUCT";
        CmbfaeExportSteps steps = logService.queryStep(current.getDate(), exchangeCode);

        if (steps.equals(CmbfaeExportSteps.STEP_02)) {

            List<InstitutionFile> files = IFileService.lookupDownloadFile(InstitutionType.Cmbfae, current.getDate(), filename -> {
                //product_111_current_receive_20170526_001.resp.txt
                if (filename.contains("product") && filename.contains("resp")) {
                    return true;
                }

                return false;
            });

            if (files.size() == 0) {
                return;
            }

            log.info("交易所返回子产品文件:" + files);


            //检查确认文件是否正确,
            if (!CommonUtil.checkFiles(files)) {

                //正确则写入日志
                logService.writeProductLog(CmbfaeExportSteps.STEP_03, "");

                //解析文件，更新子产品状态
                updateChildProduct(files);

                //备份文件
                for (InstitutionFile file : files) {
                    String filePath = file.getFileWorkspaceLocation() + File.separator + file.getFileName();
                    IFileService.backupFile(new File(filePath));
                }

            }


        }


    }


    /**
     * 检查目录下的招银文件，记录每个文件错误信息，有错误返回true
     *
     * @throws IOException
     */
    public boolean updateChildProduct(List<InstitutionFile> files) {
        boolean hasError = false;

        for (InstitutionFile institutionFile : files) {

            String filePath = institutionFile.getFileWorkspaceLocation() + File.separator + institutionFile.getFileName();
            LineIterator lineIterator = null;
            try {
                lineIterator = FileUtils.lineIterator(new File(filePath), "UTF-8");
                while (lineIterator.hasNext()) {
                    String line = lineIterator.next();
                    Map lineMap = JSON.parseObject(line, Map.class);
                    JSONObject content = (JSONObject) lineMap.get("content");
                    String childProductCode = (String) content.get("productCode");
                    scheduleProductDividedDao.updateState(childProductCode);
                }

            } catch (Exception e) {
                throw new RuntimeException("解析产品文件信息错误:" + filePath);
            } finally {
                LineIterator.closeQuietly(lineIterator);
            }

        }
        return hasError;

    }


    private void uploadFile(final String filePath) {

        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            IFileService.uploadFile(file);
        }
    }


}
