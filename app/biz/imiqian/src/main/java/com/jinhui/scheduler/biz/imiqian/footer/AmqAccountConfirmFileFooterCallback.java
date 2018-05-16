package com.jinhui.scheduler.biz.imiqian.footer;


import com.jinhui.scheduler.biz.imiqian.common.AmqAccountFileConstants;
import org.springframework.batch.item.file.FlatFileFooterCallback;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by wsc on 2017/05/21
 */
public class AmqAccountConfirmFileFooterCallback implements FlatFileFooterCallback{
    @Override
    public void writeFooter(Writer writer) throws IOException {
        String end = AmqAccountFileConstants.FILE_ENDFLAG + System.lineSeparator();//文件结束标志
        writer.write(end);
    }
}
