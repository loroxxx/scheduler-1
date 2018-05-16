package com.jinhui.scheduler.data.core.dao.support;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jinhui.scheduler.data.core.dao.FileSequenceDAO;

public class JdbcFileSequenceDAO extends JdbcDaoSupport implements FileSequenceDAO {
    public int fetchSequence(String fileKeyPattern) {
        String insertSql = "INSERT INTO schedule_file_sequence (file_key, file_seq) VALUES(?, 1) ON DUPLICATE KEY UPDATE file_seq=file_seq+1";
        String querySql = "SELECT file_seq FROM schedule_file_sequence where file_key = ?";

        getJdbcTemplate().update(insertSql, fileKeyPattern);
        int seq = getJdbcTemplate().queryForObject(querySql, Integer.class, fileKeyPattern);

        return seq;
    }
}
