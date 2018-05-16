package com.jinhui.scheduler.domain.core;

import javax.annotation.Nullable;

public interface ReceivedFileRepository {
    @Nullable
    ReceivedFile find(int fileId);
    void save(ReceivedFile file);
}
