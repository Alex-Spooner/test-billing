package com.test.billing.tests.rest;

import com.test.billing.dao.model.Balance;
import com.test.billing.tests.utils.JsonUtils;

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

        return JsonUtils.getBalanceFromJson(json);
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

    public Balance setElement(String body) {
        String json = given()
                .body(body)
                .header("Content-Type", "application/json")
                .when()
                .post(rootPath + "balance")
                .then()
                .log()
                .ifValidationFails()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("balance-schema.json"))
                .extract()
                .response()
                .body().asString();

        return JsonUtils.getBalanceFromJson(json);
    }

    public BalanceRest() {
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
