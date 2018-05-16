package com.jinhui.scheduler.biz.gzefe.service.export;

import com.jinhui.scheduler.domain.common.InstitutionFile;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */
public interface ExportService {

    /**
     * 导出贵股交的报送文件，并且ftp上传指服务器
     *
     * 1.账户申请文件和交易文件内的数据都是统计当日批次
     * 2.分红文件要统计当日批次到下个跑批日之前
     * 3.业务汇总文件包含分红和申赎记录
     */
    boolean export(String batchDate) throws Exception;


    /**
     * 查询贵股交的报送文件及状态
     */
    List<InstitutionFile> queryFiles(String batchDate);


    /**
     * 查询贵股交的文件导出状态，是否已经导出,true:导出过，false：没导出
     */
    boolean isExport(String batchDate);


    /**
     * 贵股交文件回滚操作，把日志记录删除，把股交的汇总数据删除
     */
    void rollback(int batchCode, String batchDate);


}
