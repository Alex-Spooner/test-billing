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
public class AccountDataProvider {

    @DataProvider(name = "accountPositiveCases")
    public static Iterator<Object[]> getAccountPositiveCasesFromFile() throws IOException {

        String fileSource = "cases/accountPositiveCases.txt";
        List<Object[]> list = FileUtils.getStringsFromFile(fileSource);
        return list.iterator();
    }
}
