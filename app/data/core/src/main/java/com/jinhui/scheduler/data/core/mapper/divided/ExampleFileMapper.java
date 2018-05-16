package com.jinhui.scheduler.data.core.mapper.divided;


import com.jinhui.scheduler.domain.divided.ExampleFile;

/**
 * Created by liyou on 17/3/14.
 */
public interface ExampleFileMapper {

    void save(ExampleFile log);

    void updateExampleFileStatus(ExampleFile log);

    ExampleFile find(String productNum);

}
