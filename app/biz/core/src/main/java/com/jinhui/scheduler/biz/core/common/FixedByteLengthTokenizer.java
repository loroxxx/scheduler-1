package com.jinhui.scheduler.biz.core.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.transform.AbstractLineTokenizer;
import org.springframework.batch.item.file.transform.IncorrectLineLengthException;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.file.transform.RangeArrayPropertyEditor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by jinhui on 2017/5/22.
 */
public class FixedByteLengthTokenizer extends AbstractLineTokenizer {
    private static Logger logger = LoggerFactory.getLogger(FixedByteLengthTokenizer.class);
    private Range[] ranges;

    private int maxRange = 0;

    boolean open = false;

    private String charset;
    /**
     * Set the column ranges. Used in conjunction with the
     * {@link RangeArrayPropertyEditor} this property can be set in the form of
     * a String describing the range boundaries, e.g. "1,4,7" or "1-3,4-6,7" or
     * "1-2,4-5,7-10". If the last range is open then the rest of the line is
     * read into that column (irrespective of the strict flag setting).
     *
     * @see #setStrict(boolean)
     *
     * @param ranges the column ranges expected in the input
     */
    public void setColumns(Range[] ranges) {
        this.ranges = Arrays.asList(ranges).toArray(new Range[ranges.length]);
        calculateMaxRange(ranges);
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    /*
         * Calculate the highest value within an array of ranges. The ranges aren't
         * necessarily in order. For example: "5-10, 1-4,11-15". Furthermore, there
         * isn't always a min and max, such as: "1,4-20, 22"
         */
    private void calculateMaxRange(Range[] ranges) {
        if (ranges == null || ranges.length == 0) {
            maxRange = 0;
            return;
        }

        open = false;
        maxRange = ranges[0].getMin();

        for (int i = 0; i < ranges.length; i++) {
            int upperBound;
            if (ranges[i].hasMaxValue()) {
                upperBound = ranges[i].getMax();
            }
            else {
                upperBound = ranges[i].getMin();
                if (upperBound > maxRange) {
                    open = true;
                }
            }

            if (upperBound > maxRange) {
                maxRange = upperBound;
            }
        }
    }

    @Override
    protected List<String> doTokenize(String line) {
        List<String> tokens = new ArrayList<String>(ranges.length);
        int lineLength;
        String token;
        //字节长度
        lineLength = line.getBytes().length;//line.length();
        logger.info("lineLength: " + lineLength + "  maxRange: " + maxRange);
        if (lineLength < maxRange && isStrict()) {
            throw new IncorrectLineLengthException("Line is shorter than max range " + maxRange, maxRange, lineLength, line);
        }

        /*if (!open && lineLength > maxRange && isStrict()) {
            throw new IncorrectLineLengthException("Line is longer than max range " + maxRange, maxRange, lineLength, line);
        }*/

        for (int i = 0; i < ranges.length; i++) {

            int startPos = ranges[i].getMin() - 1;
            int endPos = ranges[i].getMax();
            try {
                if (lineLength >= endPos) {
                    //以byte为单位分割字符串
                    token = byteSubString(line, startPos, endPos);//line.substring(startPos, endPos);
                } else if (lineLength >= startPos) {
                    token = byteSubString(line, startPos);//line.substring(startPos);
                } else {
                    token = "";
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            tokens.add(token);
        }

        return tokens;
    }

    private String byteSubString(String src, int byteStart) throws UnsupportedEncodingException {
        byte[] srcBytes = src.getBytes(charset);
        int len = srcBytes.length - byteStart;
        byte[] resBytes = new byte[len];
        for(int i = 0; i<len; i++){
            resBytes[i] = srcBytes[byteStart+i];
        }
        return new String(resBytes, charset);
    }

    private String byteSubString(String src, int byteStart, int byteEnd) throws UnsupportedEncodingException {
        int len = byteEnd - byteStart;
        byte[] srcBytes;
        srcBytes = src.getBytes(charset);
        byte[] resBytes = new byte[len];
        for(int i = 0; i<len; i++){
            resBytes[i] = srcBytes[byteStart+i];
        }
        return new String(resBytes, charset);
    }
}
