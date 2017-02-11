package com.test.billing.tests.balance;

import com.test.billing.dao.model.Balance;
import com.test.billing.tests.dao.BalanceDAO;
import com.test.billing.tests.rest.BalanceRest;
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
@Features("Balance CRUD")
@Stories("Balance management")
@ContextConfiguration("classpath:application-context.xml")
public class BalanceRestTest extends AbstractTestNGSpringContextTests {

    private static Logger log = new Logger();

    @Autowired
    private BalanceDAO balanceDAO;

    @Autowired
    private BalanceRest balanceRest;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
    }

    @Test(enabled = false)
    public void getBalanceByIdFromDB() {
        List<Balance> balanceList = balanceDAO.getBalanceById(1L);
        for (Balance balance : balanceList) log.info(balance.toString());
    }

    @Test(enabled = true)
    public void getBalanceByIdFromRest() {
        Balance balance = balanceRest.getElementById(1l);
        log.info(balance.toString());
    }

    @Title("Balance get by id method test")
    @Test(enabled = false, groups = {"all.smoke"})
    public void getBalanceByIdMethodPositiveTest() {

        long accountId = 1l;
        List<Balance> expectedBalanceList = balanceDAO.getBalanceById(1L);
        Balance actualBalance = balanceRest.getElementById(accountId);

        assertEquals(actualBalance.toString(), expectedBalanceList.get(0).toString());
    }

    @Title("Account get by id method negative test")
    @Test(enabled = true, groups = {"all.smoke"})
    public void getBalanceByIdMethodNegativeTest() {

        String accountId = "111";
        balanceRest.getElementByIdNegative(accountId);

    }
}
