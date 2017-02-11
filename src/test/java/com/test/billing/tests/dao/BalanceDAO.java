package com.test.billing.tests.dao;

import com.test.billing.dao.model.Balance;
import com.test.billing.tests.mapper.BalanceMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * Created by Gosh on 11.02.2017.
 * Nothing special
 */
public class BalanceDAO extends JdbcDaoSupport {
    private static final String GET_BALANCE_BY_ID = "select * from balance where balance_id =";

    private static final RowMapper<Balance> BALANCE_ROW_MAPPER = new BalanceMapper();

    public List<Balance> getBalanceById(long id) {
        return getJdbcTemplate()
                .query(GET_BALANCE_BY_ID + id, BALANCE_ROW_MAPPER);
    }


}
