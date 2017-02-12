package com.test.billing.tests.dataprovider;

import com.test.billing.tests.utils.FileUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Gosh on 12.02.2017.
 * Nothing special
 */
public class BalanceDataProvider {

    @DataProvider(name = "balancePositiveCases")
    public static Iterator<Object[]> getBalancePositiveCasesFromFile() throws IOException {

        String fileSource = "cases/balancePositiveCases.txt";
        List<Object[]> list = FileUtils.getStringsFromFile(fileSource);
        return list.iterator();
    }
}
