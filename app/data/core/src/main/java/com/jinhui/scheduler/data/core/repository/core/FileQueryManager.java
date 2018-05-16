package com.jinhui.scheduler.data.core.repository.core;

import com.jinhui.scheduler.data.core.mapper.core.GeneratedFileMapper;
import com.jinhui.scheduler.data.core.mapper.core.ReceivedFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by lenovo on 2016/11/9.
 */
@Repository("fileQueryManager")
public class FileQueryManager {
    @Autowired
    ReceivedFileMapper fileMapper;

    @Autowired
    GeneratedFileMapper generatedFileMapper;

}
