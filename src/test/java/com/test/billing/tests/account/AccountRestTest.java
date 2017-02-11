package com.test.billing.tests.account;

import com.test.billing.dao.model.Account;
import com.test.billing.tests.dao.AccountDAO;
import com.test.billing.tests.rest.AccountRest;
import com.test.billing.tests.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by Gosh on 09.02.2017.
 * Nothing special
 */
@Features("Account CRUD")
@Stories("Account management")
@ContextConfiguration("classpath:application-context.xml")
public class AccountRestTest extends AbstractTestNGSpringContextTests {


    private static Logger log = new Logger();

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountRest accountRest;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
    }

    @Test(enabled = false)
    public void getAccountByIdFromDB() {
        List<Account> accountList = accountDAO.getAccountById(1l);
        for (Account account : accountList) log.info(account.toString());
    }

    @Test(enabled = true)
    public void getBalanceByIdFromREST() {
        Account account = accountRest.getElementById(1L);
        log.info(account.toString());
    }

    @Title("Account get by id method positive test")
    @Test(enabled = true, groups = {"all.smoke"})
    public void getAccountByIdMethodPositiveTest() {

        long accountId = 1l;
        List<Account> expectedAccountList = accountDAO.getAccountById(accountId);
        Account actualAccount = accountRest.getElementById(accountId);

        assertEquals(actualAccount.toString(), expectedAccountList.get(0).toString());
    }

    @Title("Account get by id method negative test")
    @Test(enabled = true, groups = {"all.smoke"})
    public void getAccountByIdMethodNegativeTest() {

        String accountId = "111";
        accountRest.getElementByIdNegative(accountId);

    }


}
