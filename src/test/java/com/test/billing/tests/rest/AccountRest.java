package com.test.billing.tests.rest;

import com.test.billing.dao.model.Account;
import com.test.billing.tests.utils.JsonUtils;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * Created by Gosh on 11.02.2017.
 * Nothing special
 */
public class AccountRest {

    private String rootPath;

    public Account getElementById(Long id) {

        String json = given()
                .header("Content-Type", "application/json")
                .when()
                .get(rootPath + "account/" + id)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("account-schema.json"))
                .extract()
                .response()
                .body().asString();

        return JsonUtils.getAccountFromJson(json);
    }

    public void getElementByIdNegative(String id) {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get(rootPath + "account/" + id)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(404);
    }

    public Account setElement(String body) {
        String json = given()
                .body(body)
                .header("Content-Type", "application/json")
                .when()
                .post(rootPath + "account")
                .then()
                .log()
                .ifValidationFails()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("account-schema.json"))
                .extract()
                .response()
                .body().asString();

        return JsonUtils.getAccountFromJson(json);
    }

    public AccountRest() {
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
