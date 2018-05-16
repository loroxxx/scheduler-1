package com.jinhui.scheduler.domain.core;

import javax.annotation.Nullable;

public interface GeneratedFileRepository {
    @Nullable
    GeneratedFile find(int fileId);
    void save(GeneratedFile file);
}
