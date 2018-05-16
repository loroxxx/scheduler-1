package com.jinhui.scheduler.biz.zlrt.writer;


import com.jinhui.scheduler.biz.zlrt.utils.zlrt.ZlrtHttpUtils;
import com.zlink.zlpay.commonofs.common.ZlpayUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.WriteFailedException;
import org.springframework.batch.item.WriterNotOpenException;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.ResourceAwareItemWriterItemStream;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.support.AbstractItemStreamItemWriter;
import org.springframework.batch.item.util.FileUtils;
import org.springframework.batch.support.transaction.TransactionAwareBufferedWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.support.RequestContext;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Iterator;
import java.util.List;

/**
 * created by wsc
 *  2017-11-15 19:16
 **/
public class ZlrtRedeemFileWriter<T> extends AbstractItemStreamItemWriter<T> implements ResourceAwareItemWriterItemStream<T>, InitializingBean {
    private static final boolean DEFAULT_TRANSACTIONAL = true;
    protected static final Log logger = LogFactory.getLog(ZlrtRedeemFileWriter.class);
    private static final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String WRITTEN_STATISTICS_NAME = "written";
    private static final String RESTART_DATA_NAME = "current.count";
    private Resource resource;
    private ZlrtRedeemFileWriter<T>.OutputState state = null;
    private LineAggregator<T> lineAggregator;
    private boolean saveState = true;
    private boolean forceSync = false;
    private boolean shouldDeleteIfExists = true;
    private boolean shouldDeleteIfEmpty = false;
    private String encoding = "UTF-8";
    private FlatFileHeaderCallback headerCallback;
    private FlatFileFooterCallback footerCallback;
    private String lineSeparator;
    private boolean transactional;
    private boolean append;
    private String delimiter;

    public ZlrtRedeemFileWriter() {
        this.lineSeparator = DEFAULT_LINE_SEPARATOR;
        this.transactional = true;
        this.append = false;
        this.setExecutionContextName(ClassUtils.getShortName(ZlrtRedeemFileWriter.class));
    }

    public void afterPropertiesSet() throws Exception {
        //Assert.notNull(this.lineAggregator, "A LineAggregator must be provided.");
        if(this.append) {
            this.shouldDeleteIfExists = false;
        }

    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void setForceSync(boolean forceSync) {
        this.forceSync = forceSync;
    }

    public void setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    public void setLineAggregator(LineAggregator<T> lineAggregator) {
        this.lineAggregator = lineAggregator;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setEncoding(String newEncoding) {
        this.encoding = newEncoding;
    }

    public void setShouldDeleteIfExists(boolean shouldDeleteIfExists) {
        this.shouldDeleteIfExists = shouldDeleteIfExists;
    }

    public void setAppendAllowed(boolean append) {
        this.append = append;
        this.shouldDeleteIfExists = false;
    }

    public void setShouldDeleteIfEmpty(boolean shouldDeleteIfEmpty) {
        this.shouldDeleteIfEmpty = shouldDeleteIfEmpty;
    }

    public void setSaveState(boolean saveState) {
        this.saveState = saveState;
    }

    public void setHeaderCallback(FlatFileHeaderCallback headerCallback) {
        this.headerCallback = headerCallback;
    }

    public void setFooterCallback(FlatFileFooterCallback footerCallback) {
        this.footerCallback = footerCallback;
    }

    public void setTransactional(boolean transactional) {
        this.transactional = transactional;
    }

    public void write(List<? extends T> items) throws Exception {
        if(!this.getOutputState().isInitialized()) {
            throw new WriterNotOpenException("Writer must be open before it can be written to");
        } else {
            if(logger.isDebugEnabled()) {
                logger.debug("Writing to flat file with " + items.size() + " items.");
            }

            ZlrtRedeemFileWriter<T>.OutputState state = this.getOutputState();
            StringBuilder lines = new StringBuilder();

            int lineCount = 0;

            for(Iterator i$ = items.iterator(); i$.hasNext(); ++lineCount) {
                T item = (T) i$.next();
                Field[] fields = item.getClass().getDeclaredFields();
                StringBuilder bodyLines = new StringBuilder();
                lines.append("1");
                for(Field field:fields) {
                    String name = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    String type = field.getGenericType().toString();    //获取属性的类型
                    String value = "";
                    if (type.equals("class java.lang.String")) {
                        Method m = item.getClass().getMethod("get" + name);
                        value = (String) m.invoke(item);
                    }
                    if("businessType".equals(field.getName()) || "settleDate".equals(field.getName())
                            || "serialNumber".equals(field.getName()) || "systemDate".equals(field.getName())){
                        lines.append(value);
                    }else{
                        if("".equals(value) || null == value){
                            bodyLines.append(this.delimiter);
                        }else{
                            bodyLines.append(field.getName()+"="+value + this.delimiter);
                        }
                    }
                }
                logger.info("明文： " + bodyLines.toString());
                /**
                 * 加密代码
                 */
                String root = RequestContext.class.getResource("/").getFile();
                logger.info("cert: " + root + "cert/zlzf_uat.cer");
                String signLines = ZlpayUtil.signData(bodyLines.toString(), root + "cert/通用商户私钥.pfx", ZlrtHttpUtils.getKeyPwd());
                logger.info("加签： " + signLines);
                StringBuilder crypLines = new StringBuilder(ZlpayUtil.encryptData(bodyLines.toString(), root + "cert/zlzf_uat.cer"));
                logger.info("密文： " + crypLines);
                lines.append(signLines);
                lines.append(crypLines + this.lineSeparator);
            }

            try {
                state.write(lines.toString());
            } catch (IOException var7) {
                throw new WriteFailedException("Could not write data.  The file may be corrupt.", var7);
            }

            state.linesWritten += (long)lineCount;
        }
    }

    public void close() {
        super.close();
        if(this.state != null) {
            try {
                if(this.footerCallback != null && this.state.outputBufferedWriter != null) {
                    this.footerCallback.writeFooter(this.state.outputBufferedWriter);
                    this.state.outputBufferedWriter.flush();
                }
            } catch (IOException var9) {
                throw new ItemStreamException("Failed to write footer before closing", var9);
            } finally {
                this.state.close();
                if(this.state.linesWritten == 0L && this.shouldDeleteIfEmpty) {
                    try {
                        this.resource.getFile().delete();
                    } catch (IOException var8) {
                        throw new ItemStreamException("Failed to delete empty file on close", var8);
                    }
                }

                this.state = null;
            }
        }

    }

    public void open(ExecutionContext executionContext) throws ItemStreamException {
        super.open(executionContext);
        Assert.notNull(this.resource, "The resource must be set");
        if(!this.getOutputState().isInitialized()) {
            this.doOpen(executionContext);
        }

    }

    private void doOpen(ExecutionContext executionContext) throws ItemStreamException {
        ZlrtRedeemFileWriter<T>.OutputState outputState = this.getOutputState();
        if(executionContext.containsKey(this.getExecutionContextKey("current.count"))) {
            outputState.restoreFrom(executionContext);
        }

        try {
            outputState.initializeBufferedWriter();
        } catch (IOException var5) {
            throw new ItemStreamException("Failed to initialize writer", var5);
        }

        if(outputState.lastMarkedByteOffsetPosition == 0L && !outputState.appending && this.headerCallback != null) {
            try {
                this.headerCallback.writeHeader(outputState.outputBufferedWriter);
                outputState.write(this.lineSeparator);
            } catch (IOException var4) {
                throw new ItemStreamException("Could not write headers.  The file may be corrupt.", var4);
            }
        }

    }

    public void update(ExecutionContext executionContext) {
        super.update(executionContext);
        if(this.state == null) {
            throw new ItemStreamException("ItemStream not open or already closed.");
        } else {
            Assert.notNull(executionContext, "ExecutionContext must not be null");
            if(this.saveState) {
                try {
                    executionContext.putLong(this.getExecutionContextKey("current.count"), this.state.position());
                } catch (IOException var3) {
                    throw new ItemStreamException("ItemStream does not return current position properly", var3);
                }

                executionContext.putLong(this.getExecutionContextKey("written"), this.state.linesWritten);
            }

        }
    }

    private ZlrtRedeemFileWriter<T>.OutputState getOutputState() {
        if(this.state == null) {
            File file;
            try {
                file = this.resource.getFile();
            } catch (IOException var3) {
                throw new ItemStreamException("Could not convert resource to file: [" + this.resource + "]", var3);
            }

            Assert.state(!file.exists() || file.canWrite(), "Resource is not writable: [" + this.resource + "]");
            this.state = new ZlrtRedeemFileWriter.OutputState();
            this.state.setDeleteIfExists(this.shouldDeleteIfExists);
            this.state.setAppendAllowed(this.append);
            this.state.setEncoding(this.encoding);
        }

        return this.state;
    }

    private class OutputState {
        private static final String DEFAULT_CHARSET = "UTF-8";
        private FileOutputStream os;
        Writer outputBufferedWriter;
        FileChannel fileChannel;
        String encoding;
        boolean restarted;
        long lastMarkedByteOffsetPosition;
        long linesWritten;
        boolean shouldDeleteIfExists;
        boolean initialized;
        private boolean append;
        private boolean appending;

        private OutputState() {
            this.encoding = "UTF-8";
            this.restarted = false;
            this.lastMarkedByteOffsetPosition = 0L;
            this.linesWritten = 0L;
            this.shouldDeleteIfExists = true;
            this.initialized = false;
            this.append = false;
            this.appending = false;
        }

        public long position() throws IOException {
            long pos = 0L;
            if(this.fileChannel == null) {
                return 0L;
            } else {
                this.outputBufferedWriter.flush();
                pos = this.fileChannel.position();
                if(ZlrtRedeemFileWriter.this.transactional) {
                    pos += ((TransactionAwareBufferedWriter)this.outputBufferedWriter).getBufferSize();
                }

                return pos;
            }
        }

        public void setAppendAllowed(boolean append) {
            this.append = append;
        }

        public void restoreFrom(ExecutionContext executionContext) {
            this.lastMarkedByteOffsetPosition = executionContext.getLong(ZlrtRedeemFileWriter.this.getExecutionContextKey("current.count"));
            this.linesWritten = executionContext.getLong(ZlrtRedeemFileWriter.this.getExecutionContextKey("written"));
            if(ZlrtRedeemFileWriter.this.shouldDeleteIfEmpty && this.linesWritten == 0L) {
                this.restarted = false;
                this.lastMarkedByteOffsetPosition = 0L;
            } else {
                this.restarted = true;
            }

        }

        public void setDeleteIfExists(boolean shouldDeleteIfExists) {
            this.shouldDeleteIfExists = shouldDeleteIfExists;
        }

        public void setEncoding(String encoding) {
            this.encoding = encoding;
        }

        public void close() {
            this.initialized = false;
            this.restarted = false;

            try {
                if(this.outputBufferedWriter != null) {
                    this.outputBufferedWriter.close();
                }
            } catch (IOException var5) {
                throw new ItemStreamException("Unable to close the the ItemWriter", var5);
            } finally {
                if(!ZlrtRedeemFileWriter.this.transactional) {
                    this.closeStream();
                }

            }

        }

        private void closeStream() {
            try {
                if(this.fileChannel != null) {
                    this.fileChannel.close();
                }
            } catch (IOException var9) {
                throw new ItemStreamException("Unable to close the the ItemWriter", var9);
            } finally {
                try {
                    if(this.os != null) {
                        this.os.close();
                    }
                } catch (IOException var8) {
                    throw new ItemStreamException("Unable to close the the ItemWriter", var8);
                }

            }

        }

        public void write(String line) throws IOException {
            if(!this.initialized) {
                this.initializeBufferedWriter();
            }

            this.outputBufferedWriter.write(line);
            this.outputBufferedWriter.flush();
        }

        public void truncate() throws IOException {
            this.fileChannel.truncate(this.lastMarkedByteOffsetPosition);
            this.fileChannel.position(this.lastMarkedByteOffsetPosition);
        }

        private void initializeBufferedWriter() throws IOException {
            File file = ZlrtRedeemFileWriter.this.resource.getFile();
            FileUtils.setUpOutputFile(file, this.restarted, this.append, this.shouldDeleteIfExists);
            this.os = new FileOutputStream(file.getAbsolutePath(), true);
            this.fileChannel = this.os.getChannel();
            this.outputBufferedWriter = this.getBufferedWriter(this.fileChannel, this.encoding);
            this.outputBufferedWriter.flush();
            if(this.append && file.length() > 0L) {
                this.appending = true;
            }

            Assert.state(this.outputBufferedWriter != null);
            if(this.restarted) {
                this.checkFileSize();
                this.truncate();
            }

            this.initialized = true;
        }

        public boolean isInitialized() {
            return this.initialized;
        }

        private Writer getBufferedWriter(final FileChannel fileChannel, String encoding) {
            try {
                if(ZlrtRedeemFileWriter.this.transactional) {
                    TransactionAwareBufferedWriter writer = new TransactionAwareBufferedWriter(fileChannel, new Runnable() {
                        public void run() {
                            ZlrtRedeemFileWriter.OutputState.this.closeStream();
                        }
                    });
                    writer.setEncoding(encoding);
                    writer.setForceSync(ZlrtRedeemFileWriter.this.forceSync);
                    return writer;
                } else {
                    Writer writerx = new BufferedWriter(Channels.newWriter(fileChannel, encoding)) {
                        public void flush() throws IOException {
                            super.flush();
                            if(ZlrtRedeemFileWriter.this.forceSync) {
                                fileChannel.force(false);
                            }

                        }
                    };
                    return writerx;
                }
            } catch (UnsupportedCharsetException var5) {
                throw new ItemStreamException("Bad encoding configuration for output file " + fileChannel, var5);
            }
        }

        private void checkFileSize() throws IOException {
            long size = -1L;
            this.outputBufferedWriter.flush();
            size = this.fileChannel.size();
            if(size < this.lastMarkedByteOffsetPosition) {
                throw new ItemStreamException("Current file size is smaller than size at last commit");
            }
        }
    }
}
