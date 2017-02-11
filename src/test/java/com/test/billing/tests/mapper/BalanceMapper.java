package com.test.billing.tests.mapper;

import com.test.billing.dao.model.Balance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Gosh on 09.02.2017.
 * Nothing special
 */
public class BalanceMapper implements RowMapper<Balance> {
    private static final String BALANCE_ID = "balance_id";
    private static final String AMOUNT = "amount";
    private static final String CRE_DATE = "cre_date";


    public Balance mapRow(ResultSet r, int i) throws SQLException {
        return new Balance(r.getLong(BALANCE_ID),
                r.getFloat(AMOUNT),
                r.getDate(CRE_DATE));
    }
}
