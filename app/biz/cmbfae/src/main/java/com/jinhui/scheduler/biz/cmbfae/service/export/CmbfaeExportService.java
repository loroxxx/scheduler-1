package com.jinhui.scheduler.biz.cmbfae.service.export;

import com.jinhui.scheduler.domain.common.InstitutionFile;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */
public interface CmbfaeExportService {

    /**
     * 导出招银的报送文件，然后由定时任务按交易文件，持仓文件，收益文件的顺序上传到ftp服务器
     * 1.交易文件内的数据都是统计当日批次
     * 2.持仓文件，给交易所发送最新的持仓记录
     * 3.收益文件包含当日批次到下一个批次日之前的收益率
     */
    boolean export(String batchDate) throws Exception;


    /**
     *查询招银的报送文件及状态
     */
    List<InstitutionFile> queryFiles(String batchDate);


    /**
     * 查询招银的文件导出状态，是否已经导出,true:导出过，false：没导出
     */
    boolean isExport(String batchDate);


    /**
     * 招银文件回滚操作，把日志记录删除，把招银的汇总数据删除
     */
    void rollback(int batchCode, String batchDate);



}
