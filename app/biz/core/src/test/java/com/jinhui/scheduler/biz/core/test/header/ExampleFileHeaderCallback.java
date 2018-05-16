package com.jinhui.scheduler.biz.core.test.header;


import org.springframework.batch.item.file.FlatFileHeaderCallback;
import java.io.IOException;
import java.io.Writer;

/**
 * 文件头的预处理回调逻辑
 */
public class ExampleFileHeaderCallback implements FlatFileHeaderCallback {

    @Override
    public void writeHeader(Writer writer) throws IOException {
        /**
         * todo 文件头逻辑
         */
    }

}
