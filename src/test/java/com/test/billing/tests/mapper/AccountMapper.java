package com.test.billing.tests.mapper;

import com.test.billing.dao.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Gosh on 09.02.2017.
 * Nothing special
 */
public class AccountMapper implements RowMapper<Account> {
    private static final String ID = "account_id";
    private static final String FIRST_NAME = "first_name";
    private static final String SECOND_NAME = "second_name";
    private static final String LAST_NAME = "last_name";
    private static final String CRE_DATE = "cre_date";

    public Account mapRow(ResultSet r, int i) throws SQLException {
        return new Account(r.getLong(ID),
                r.getString(FIRST_NAME), r.getString(SECOND_NAME),
                r.getString(LAST_NAME),
                r.getDate(CRE_DATE));
    }
}
