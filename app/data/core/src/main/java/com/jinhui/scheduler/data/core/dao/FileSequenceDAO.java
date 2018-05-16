package com.jinhui.scheduler.data.core.dao;

public interface FileSequenceDAO {
    int fetchSequence(String fileKeyPattern);
}
