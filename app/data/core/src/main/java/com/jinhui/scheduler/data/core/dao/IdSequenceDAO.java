package com.jinhui.scheduler.data.core.dao;

import com.jinhui.scheduler.domain.common.SequenceKey;

public interface IdSequenceDAO {
    long fetchSequence(SequenceKey key);
}
