package com.test.billing.tests.rest;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.billing.dao.model.Balance;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * Created by Gosh on 11.02.2017.
 * Nothing special
 */

public class BalanceRest {

    private String rootPath;

    public Balance getElementById(Long id) {
        String json = given()
                .header("Content-Type", "application/json")
                .when()
                .get(rootPath + "balance/" + id)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("balance-schema.json"))
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

    public void getElementByIdNegative(String id) {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get(rootPath + "balance/" + id)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(404);
    }

    public BalanceRest() {
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
