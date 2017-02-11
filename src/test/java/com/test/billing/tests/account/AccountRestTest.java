package com.test.billing.tests.account;

import com.test.billing.dao.model.Account;
import com.test.billing.tests.dao.AccountDAO;
import com.test.billing.tests.rest.AccountRest;
import com.test.billing.tests.rest.BalanceRest;
import io.restassured.response.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Gosh on 09.02.2017.
 * Nothing special
 */

@ContextConfiguration("classpath:application-context.xml")
public class AccountRestTest extends AbstractTestNGSpringContextTests {

    private static Logger log = LoggerFactory.getLogger(AccountRestTest.class);

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountRest accountRest;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
    }

    @Test
    public void getAccountByIdFromDB() {
        List<Account> accountList = accountDAO.getAccountById(1l);
        for (Account account : accountList) log.info(account.toString());
    }

    @Test
    public void getBalanceByIdFromREST(){
        Account account = accountRest.getAccountById(1L);
        log.info(account.toString());
    }


}
