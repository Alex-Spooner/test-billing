package com.test.billing.tests.rest;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.billing.dao.model.Account;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static io.restassured.RestAssured.given;

/**
 * Created by Gosh on 11.02.2017.
 * Nothing special
 */
public class AccountRest {

    private String rootPath;

    public Account getAccountById(Long id) {
        String json = given()
                .header("Content-Type", "application/json")
                .when()
                .get(rootPath + "account/" + id)
                .then()
                .extract()
                .response()
                .body().asString();

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

    public AccountRest() {
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
