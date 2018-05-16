package com.jinhui.scheduler.web.api.controller.exchange;

import com.jinhui.scheduler.biz.cmbfae.service.export.CmbfaeExportService;
import com.jinhui.scheduler.biz.gzefe.service.export.ExportService;
import com.jinhui.scheduler.data.core.mapper.core.ExchangeInfoMapper;
import com.jinhui.scheduler.data.core.mapper.imiqian.BatchStateCurrentMapper;
import com.jinhui.scheduler.domain.common.ExchangeType;
import com.jinhui.scheduler.domain.common.InstitutionFile;
import com.jinhui.scheduler.domain.common.InstitutionType;
import com.jinhui.scheduler.domain.core.ExchangeInfo;
import com.jinhui.scheduler.domain.imiqian.domain.BatchStateCurrent;
import com.jinhui.scheduler.web.api.vo.base.SimpleWebResult;
import com.jinhui.scheduler.web.api.vo.base.WebConstants;
import com.jinhui.scheduler.web.api.vo.file.InstitutionFileAssembler;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/exchangeInfo")
public class ExchangeInfoController {
    private static Logger logger = LoggerFactory.getLogger(ExchangeInfoController.class);

    @Autowired
    private ExchangeInfoMapper exchangeInfoDao;

    @Autowired
    private ExportService gzefeExportService;

    @Autowired
    private CmbfaeExportService cmbfaeExportService;


    @Autowired
    private BatchStateCurrentMapper batchStateCurrentMapper;

    private final static BigDecimal ten_thousand = new BigDecimal("10000");


    /**
     * 查询交易所列表
     *
     * @return
     */
    @RequestMapping(value = "/queryExchanges", method = RequestMethod.GET)
    @ResponseBody
    public SimpleWebResult queryExchanges() {
        List<ExchangeInfo> exchanges = exchangeInfoDao.queryExchanges();
        SimpleWebResult ok = SimpleWebResult.ok();
        ok.setData(exchanges);
        return ok;
    }


    /**
     * 更新交易所额度
     *
     * @return
     */
    @RequestMapping(value = "/updateExchange", method = RequestMethod.POST)
    @ResponseBody
    public SimpleWebResult updateExchange(@RequestBody ExchangeInfo exchangeInfo) {

        //前端传过来的额度是万元单位,要转换为元再保存
        BigDecimal currentProductLimit = exchangeInfo.getCurrentProductLimit();
        exchangeInfo.setCurrentProductLimit(currentProductLimit.multiply(ten_thousand));

        exchangeInfoDao.updateExchangeLimit(exchangeInfo.getExchangeCode(), exchangeInfo.getCurrentProductLimit());

        return SimpleWebResult.ok();
    }

    /**
     * 导出交易所的文件
     *
     * @return
     */
    @RequestMapping(value = "/exportExchangeFile", method = RequestMethod.GET)
    @ResponseBody
    public SimpleWebResult exportExchangeFile(@RequestParam("exchangeCode") String exchangeCode) throws Exception {

        BatchStateCurrent currBatchState = batchStateCurrentMapper.findCurrentBatch();
        String batchDate = currBatchState.getDate();

        //如果导出过了，就不再导出


        if (exchangeCode.equals(ExchangeType.Gzefe.getExchangeNo())) {

            boolean export = gzefeExportService.isExport(batchDate);
            if (!export) {
                gzefeExportService.export(batchDate);
            }
            SimpleWebResult result = SimpleWebResult.ok();
            return result;
        }

        if (exchangeCode.equals(ExchangeType.Cmbfae.getExchangeNo())) {

            boolean export = cmbfaeExportService.isExport(batchDate);
            if (!export) {
                cmbfaeExportService.export(batchDate);
            }
            SimpleWebResult result = SimpleWebResult.ok();
            return result;
        }


        return SimpleWebResult.error("不支持的交易所");
    }

    /**
     * 查询交易所文件及状态
     *
     * @return
     */
    @RequestMapping(value = "/queryExchangeFile", method = RequestMethod.GET)
    @ResponseBody
    public SimpleWebResult queryExchangeFile(@RequestParam("exchangeCode") String exchangeCode) {

        BatchStateCurrent currBatchState = batchStateCurrentMapper.findCurrentBatch();
        String batchDate = currBatchState.getDate();

        if (exchangeCode.equals(ExchangeType.Gzefe.getExchangeNo())) {
            List<InstitutionFile> institutionFiles = gzefeExportService.queryFiles(batchDate);
            SimpleWebResult result = SimpleWebResult.ok();
            result.setData(InstitutionFileAssembler.toVoList(institutionFiles));
            return result;
        }

        if (exchangeCode.equals(ExchangeType.Cmbfae.getExchangeNo())) {
            List<InstitutionFile> institutionFiles = cmbfaeExportService.queryFiles(batchDate);
            SimpleWebResult result = SimpleWebResult.ok();
            result.setData(InstitutionFileAssembler.toVoList(institutionFiles));
            return result;
        }


        return SimpleWebResult.error("不支持的交易所");
    }

    /**
     * 查询导出文件是否成功
     */
    @RequestMapping(value = "/findExportResult", method = RequestMethod.GET)
    @ResponseBody
    public SimpleWebResult findExportResult(@RequestParam("exchangeCode") String exchangeCode) {


        BatchStateCurrent currBatchState = batchStateCurrentMapper.findCurrentBatch();
        String batchDate = currBatchState.getDate();

        if (exchangeCode.equals(ExchangeType.Gzefe.getExchangeNo())) {
            boolean export = gzefeExportService.isExport(batchDate);
            if (export) {
                return SimpleWebResult.ok();
            } else {
                return SimpleWebResult.error("贵股交文件导出失败");
            }
        }


        if (exchangeCode.equals(ExchangeType.Cmbfae.getExchangeNo())) {
            boolean export = cmbfaeExportService.isExport(batchDate);
            if (export) {
                return SimpleWebResult.ok();
            } else {
                return SimpleWebResult.error("招银前海文件导出失败");
            }
        }


        return SimpleWebResult.error("不支持的交易所");

    }


    /**
     * 回滚,清除exchange_file_log中对应交易所的记录
     */


}
