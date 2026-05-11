package data.pages;

import data.Settings;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RegistrationApi {

    @Step("Регистрация пользователя с именем: {name} , паролем: {password} и имейлом: {email}")
    public Response registerUser(String name, String email, String password) {
        Map<String, String> json = new HashMap<>();

        if (name != null) json.put("name", name);
        if (password != null) json.put("password", password);
        if (email != null) json.put("email", email);

        Response response = given()
                .baseUri(Settings.getBaseUrl())
                .header("Content-type", "application/json")
                .body(json)
                .when()
                .post("/api/auth/register");

        return response;
    }

    @Step("Удаление пользователя с токеном: {accessToken}")
    public Response deleteUser(String accessToken) {

        Response response = given()
                .baseUri(Settings.getBaseUrl())
                .header("Content-type", "application/json")
                .auth().oauth2(accessToken)
                .when()
                .delete("/api/auth/user");

        return response;
    }

    @Step("Авторизация пользователя с именем: {name} , паролем: {password}")
    public Response loginUser(String name, String password) {
        Map<String, String> json = new HashMap<>();

        if (name != null) json.put("email", name);
        if (password != null) json.put("password", password);

        Response response = given()
                .baseUri(Settings.getBaseUrl())
                .header("Content-type", "application/json")
                .body(json)
                .when()
                .post("/api/auth/login");

        return response;
    }
}
