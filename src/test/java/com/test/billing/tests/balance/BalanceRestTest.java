package com.test.billing.tests.balance;

import com.test.billing.dao.model.Balance;
import com.test.billing.tests.dao.BalanceDAO;
import com.test.billing.tests.dataprovider.BalanceDataProvider;
import com.test.billing.tests.rest.BalanceRest;
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

    @Title("Get by id method positive test")
    @Test(enabled = true, groups = {"all.rest"})
    public void getBalanceByIdMethodPositiveTest() {

        long accountId = 1l;
        List<Balance> expectedBalanceList = balanceDAO.getBalanceById(1L);
        Balance actualBalance = balanceRest.getElementById(accountId);

        assertEquals(actualBalance.toString(), expectedBalanceList.get(0).toString());
    }

    @Title("Get by id method negative test")
    @Test(enabled = true, groups = {"all.rest"})
    public void getBalanceByIdMethodNegativeTest() {

        String accountId = "111222";
        balanceRest.getElementByIdNegative(accountId);

    }

    @Title("Post method positive test")
    @Test(enabled = true, groups = {"all.rest"}, dataProviderClass = BalanceDataProvider.class, dataProvider = "balancePositiveCases")
    public void postMethodPositiveTest(String expectedBalanceData) {

        Balance expectedBalance = JsonUtils.getBalanceFromJson(expectedBalanceData);
        long balanceId = expectedBalance.getBalanceId();
        balanceDAO.deleteBalanceById(balanceId);

        Balance actualResponseBalance = balanceRest.setElement(expectedBalanceData);
        List<Balance> actualBalanceList = balanceDAO.getBalanceById(balanceId);

        assertEquals(actualResponseBalance.toString(), actualBalanceList.get(0).toString(), "Balance from the response body should be equal to created balance!");
        assertEquals(actualBalanceList.get(0).toString(), expectedBalance.toString());

        balanceDAO.deleteBalanceById(balanceId);
    }
}
