package com.test.billing.tests.account;

import com.test.billing.dao.model.Account;
import com.test.billing.tests.dao.AccountDAO;
import com.test.billing.tests.dataprovider.AccountDataProvider;
import com.test.billing.tests.rest.AccountRest;
import com.test.billing.tests.utils.JsonUtils;
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

    @Title("Get by id method positive test")
    @Test(enabled = true, groups = {"all.rest"})
    public void getAccountByIdMethodPositiveTest() {

        long accountId = 1l;
        List<Account> expectedAccountList = accountDAO.getAccountById(accountId);
        Account actualAccount = accountRest.getElementById(accountId);

        assertEquals(actualAccount.toString(), expectedAccountList.get(0).toString());
    }

    @Title("Get by id method negative test")
    @Test(enabled = true, groups = {"all.rest"})
    public void getAccountByIdMethodNegativeTest() {

        String accountId = "111222333";
        accountRest.getElementByIdNegative(accountId);

    }

    @Title("Post method positive test")
    @Test(enabled = true, groups = {"all.rest"}, dataProviderClass = AccountDataProvider.class, dataProvider = "accountPositiveCases")
    public void postMethodPositiveTest(String expectedAccountData) {

        Account expectedAccount = JsonUtils.getAccountFromJson(expectedAccountData);
        long accountId = expectedAccount.getAccountId();
        accountDAO.deleteAccountById(accountId);

        Account actualResponseAccount = accountRest.setElement(expectedAccountData);
        List<Account> actualAccountList = accountDAO.getAccountById(accountId);

        assertEquals(actualResponseAccount.toString(), actualAccountList.get(0).toString(), "Account from the response body should be equal to created account!");
        assertEquals(actualAccountList.get(0).toString(), expectedAccount.toString());

        accountDAO.deleteAccountById(accountId);
    }


}
