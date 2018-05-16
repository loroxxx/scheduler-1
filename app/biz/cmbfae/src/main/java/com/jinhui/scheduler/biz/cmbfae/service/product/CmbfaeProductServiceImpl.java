package com.jinhui.scheduler.biz.cmbfae.service.product;

import static com.jinhui.scheduler.biz.cmbfae.util.TypeConverter.String2BigDecimal;
import static com.jinhui.scheduler.biz.cmbfae.util.TypeConverter.String2Date;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jinhui.scheduler.biz.cmbfae.constans.CmbfaeConst;
import com.jinhui.scheduler.biz.cmbfae.constans.CmbfaeExportSteps;
import com.jinhui.scheduler.biz.cmbfae.service.log.LogService;
import com.jinhui.scheduler.biz.cmbfae.service.product.divided.ProductDividedService;
import com.jinhui.scheduler.biz.core.batch.BatchStateService;
import com.jinhui.scheduler.biz.core.common.IFileDirectoryService;
import com.jinhui.scheduler.biz.core.common.IFileNameService;
import com.jinhui.scheduler.biz.core.exception.BizException;
import com.jinhui.scheduler.biz.core.file.support.Sftp;
import com.jinhui.scheduler.biz.core.product.service.ChnProductService;
import com.jinhui.scheduler.biz.core.util.ProductUtils;
import com.jinhui.scheduler.data.core.mapper.cmbfae.ExchangeFileLogDao;
import com.jinhui.scheduler.domain.cmbfae.ExchangeFileLog;
import com.jinhui.scheduler.domain.cmbfae.model.*;
import com.jinhui.scheduler.domain.common.ExchangeType;
import com.jinhui.scheduler.domain.core.ProductVo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jinhui.scheduler.biz.cmbfae.util.CommonUtil;
import com.jinhui.scheduler.biz.core.common.IFileService;
import com.jinhui.scheduler.data.core.mapper.cmbfae.CmbfaeProductDao;
import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionType;
import org.springframework.transaction.annotation.Transactional;

@Service("CmbfaeProductService")
public class CmbfaeProductServiceImpl implements CmbfaeProductService {
    private Logger log = LoggerFactory.getLogger(CmbfaeProductServiceImpl.class);

    private static final BigDecimal RATE_UNIT = new BigDecimal(0.01);

    @Autowired
    private CmbfaeProductDao productDao;

    @Autowired
    private BatchStateService batchStateService;

    @Autowired
    private IFileService IFileService;

    @Autowired
    private ExchangeFileLogDao exchangeFileLogMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Product> importProductInfo() throws Exception {

        //获取当前批次时间
        final BatchState current = batchStateService.getBatchStateCurrent();

        //检入文件
        List<InstitutionFile> files = IFileService.lookupDownloadFile(InstitutionType.Cmbfae, current.getDate(), filename -> {
            if (filename.contains("product_111_current_publish")) {
                //没导入过该文件，则检入
                int count = exchangeFileLogMapper.queryProductFileCount(current.getDate(), ExchangeType.Cmbfae.getExchangeNo() + "_PRODUCT");
                if (count == 0) {
                    return true;
                }
            }
            return false;
        });

        if (files.size() == 0) {
            log.info("没有检测到可导入的产品文件");
            throw new BizException("没有检测到可导入的产品文件");
        }


        //导入产品
        List<Product> products = null;
        for (InstitutionFile file : files) {

            //处理文件
            products = processFile(file, current.getDate());
            //写入日志
            writeLog(CmbfaeExportSteps.STEP_0, current);

            //备份文件
            String filePath = file.getFileWorkspaceLocation() + File.separator + file.getFileName();
            IFileService.backupFile(new File(filePath));
        }

        return products;
    }


    /**
     * 处理产品文件
     *
     * @param file 机构文件
     * @throws Exception
     */

    private List<Product> processFile(InstitutionFile file, Date currentDate) throws Exception {

        ArrayList list = new ArrayList<Product>();
        String filePath = file.getFileWorkspaceLocation() + File.separator + file.getFileName();
        log.info("产品文件开始导入:" + filePath);
        LineIterator lineIterator = null;
        try {
            lineIterator = FileUtils.lineIterator(new File(filePath), "UTF-8");
            while (lineIterator.hasNext()) {
                String line = lineIterator.next();
                Map<String, Object> lineMap = JSON.parseObject(line, Map.class);
                Map<String, String> content = (Map<String, String>) lineMap.get("content");

                // 把Map映射到Product中
                Product p = new Product();
                MappingProduct(content, p, currentDate);

                // 先查询是否有相同的产品，如果有，提示报错;
                Product product = productDao.queryProduct(p);
                if (product != null) {


                    throw new BizException("数据库中已经有存在的产品:" + product.getProductNo());
                }
                // 插入新产品
                productDao.save(p);
                list.add(p);
            }
        } finally {
            LineIterator.closeQuietly(lineIterator);
        }

        return list;
    }


    private void MappingProduct(Map<String, String> content, Product p, Date currentDate) {
        try {
            p.setCalIncomeWay(content.get("profitCalcType"));
            p.setCalRateWay(content.get("calcInterestMethod"));
            p.setCasheDate(null);
            p.setEndPeriods(String2BigDecimal(content.get("endTerm")));
            String exchangeNo = ExchangeType.Cmbfae.getExchangeNo();
            p.setExchangeNo(exchangeNo);
//            p.setIncomeRate(String2BigDecimal(content.get("expectedYield")).multiply(RATE_UNIT));//收益率要乘以百分比

            BigDecimal expectedYield = String2BigDecimal(content.get("expectedYield")).multiply(RATE_UNIT);
            p.setNominalIncomeRate(expectedYield);//名义收益率
            p.setNominalPercentIncomeRate(ProductUtils.calProfit(expectedYield));//名义万元收益

            p.setIpoStartDate(String2Date(content.get("ipoBeginTime"), "yyyyMMddhhmmss"));
            p.setParentProductNo(null);
            p.setPlatProductNo(content.get("productCode"));

            String coProductCode = content.get("coProductCode");
            if (coProductCode == null || coProductCode.equals("")) {
                throw new RuntimeException("合作方代码（金飞镖代码）不能为空");
            }
            p.setProductNo(coProductCode);
            p.setProductType("0");
            BigDecimal sumLismit = String2BigDecimal(content.get("productSumLimit"));
            BigDecimal sumvol = String2BigDecimal(content.get("productSumVolume"));
            p.setProductVol(sumLismit.divide(sumvol));

            p.setProductName(content.get("productName"));
            p.setSetupDate(String2Date(content.get("establishDate"), "yyyyMMdd"));
            p.setTotalLimit(String2BigDecimal(content.get("productSumLimit")));
            p.setTotalVol(String2BigDecimal(content.get("productSumVolume")));
            p.setStartPeriods(String2BigDecimal(content.get("beginTerm")));
            p.setEndPeriods(String2BigDecimal(content.get("endTerm")));
            p.setSubsStartAmount(String2BigDecimal(content.get("purchaseBeginAmount")));
            p.setSubsRange(String2BigDecimal(content.get("purchaseScopeAmount")));
            p.setCalIncomeWay(content.get("profitCalcType"));
            p.setCalRateWay(content.get("calcInterestMethod"));
            p.setRiskAssess(content.get("isNeedRisk"));
            p.setRiskLevel(content.get("riskLevel"));
            p.setCreateTime(currentDate);
        } catch (Exception e) {
            log.error("读取产品信息出错");
            throw new RuntimeException(e);
        }
    }


    private void writeLog(CmbfaeExportSteps steps, BatchState current) {

        ExchangeFileLog log = new ExchangeFileLog();

        log.setStep(steps.name());
        log.setBatchCode(current.getBatchCode());
        log.setBatchDate(current.getDate());
        log.setCreateTime(new Date());
        log.setStepCode(steps.getCode());
        log.setStepDesc(steps.getDesc());
        log.setStatus("0");
        log.setExchangeCode(InstitutionType.Cmbfae.getAbbr());
        exchangeFileLogMapper.insert(log);

    }

}
