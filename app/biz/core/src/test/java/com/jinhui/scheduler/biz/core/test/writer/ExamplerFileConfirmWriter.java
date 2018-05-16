package com.jinhui.scheduler.biz.core.test.writer;


import com.jinhui.scheduler.data.core.mapper.divided.ExampleFileMapper;
import com.jinhui.scheduler.domain.divided.FileStatus;
import com.jinhui.scheduler.domain.divided.ExampleFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ExamplerFileConfirmWriter implements ItemWriter<String>{

    @Autowired
    private ExampleFileMapper unpackingFileMapper;

    private final static Logger logger = LoggerFactory.getLogger(ExamplerFileConfirmWriter.class);

    @Override
    public void write(List<? extends String> items) throws Exception {
        if(logger.isInfoEnabled())
            logger.info("收到: {} ",items.toString());
        String parentProductCode = items.get(0);
        ExampleFile unpackingFile = new ExampleFile(parentProductCode, FileStatus.RECEIVED);
        unpackingFileMapper.updateExampleFileStatus(unpackingFile);
        if(logger.isInfoEnabled())
            logger.info("更新对端父产品ID={}， 对应文件状态", items.get(0));
    }

    public static void main(String[] strs){
        System.out.println("run----");
        Test1 test1 = new Test1();

        try {
            new Test2().interrupte();
        } catch (Exception e) {
            System.out.println("catch exception");
        }finally {
            test1.test();
        }
    }

    static class Test1 implements Thread.UncaughtExceptionHandler{
        public void test(){
            if(Thread.currentThread().isInterrupted())
                System.out.println("interrupted");
            else {
                System.out.println("run over");
            }
        }

        public Test1() {
            Thread.currentThread().setUncaughtExceptionHandler(this);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("uncaughtException  ===> "+t.getId());
        }
    }

    static class Test2{
        public  void interrupte(){
            Thread.currentThread().interrupt();
            throw new Error("error");
        }
    }


}
