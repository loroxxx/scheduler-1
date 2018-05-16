package com.jinhui.scheduler.data.core.dao.support;

import com.jinhui.scheduler.data.core.dao.IdSequenceDAO;
import com.jinhui.scheduler.domain.common.SequenceKey;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcIdSequenceDAO extends JdbcDaoSupport implements IdSequenceDAO {

    public long fetchSequence(SequenceKey key) {
        String insertSql = "INSERT INTO schedule_id_sequence (seq_key, seq) " +
                "VALUES(?, 1) ON DUPLICATE KEY UPDATE seq=seq+1";
        String querySql = "SELECT seq FROM schedule_id_sequence where seq_key = ?";

        getJdbcTemplate().update(insertSql, key.getKey());
        long seq = getJdbcTemplate().queryForObject(querySql, Long.class, key.getKey());

        return seq;
    }
}
