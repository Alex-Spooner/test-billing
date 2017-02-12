package com.test.billing.tests.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.billing.dao.model.Account;
import com.test.billing.dao.model.Balance;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Gosh on 12.02.2017.
 * Nothing special
 */
public class JsonUtils {


    public static Account getAccountFromJson(String json) {

        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);

        Account account = null;
        try {
            account = mapper.readValue(json, Account.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return account;
    }

    public static Balance getBalanceFromJson(String json) {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);

        Balance balance = null;
        try {
            balance = mapper.readValue(json, Balance.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return balance;
    }
}
