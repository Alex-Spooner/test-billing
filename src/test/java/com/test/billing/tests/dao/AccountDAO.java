package com.test.billing.tests.dao;

import com.test.billing.dao.model.Account;
import com.test.billing.tests.mapper.AccountMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * Created by Gosh on 09.02.2017.
 * Nothing special
 */
public class AccountDAO extends JdbcDaoSupport {

    private static final String GET_ACCOUNT_BY_ID = "select * from account where account_id =";

    private static final RowMapper<Account> ACCOUNT_ROW_MAPPER = new AccountMapper();

    public List<Account> getAccountById(long id){
        return getJdbcTemplate()
                .query(GET_ACCOUNT_BY_ID + id, ACCOUNT_ROW_MAPPER);
    }


}
