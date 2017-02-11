package com.test.billing.tests.rest;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.billing.dao.model.Balance;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static io.restassured.RestAssured.given;

/**
 * Created by Gosh on 11.02.2017.
 * Nothing special
 */

@Component
public class BalanceRest {

    private String rootPath;

    public Balance getBalanceById(Long id) {
        String json = given()
                .header("Content-Type", "application/json")
                .when()
                .get(rootPath + "balance/" + id)
                .then()
                .extract()
                .response()
                .body().asString();

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

    public BalanceRest() {
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
