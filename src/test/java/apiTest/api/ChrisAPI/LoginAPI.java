package apiTest.api.ChrisAPI;

import apiTest.api.BaseAPI;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.apache.poi.poifs.crypt.agile.AgileEncryptionVerifier;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoginAPI extends BaseAPI {
    public LoginAPI(){
        requestSpec.basePath("/rest/auth/1/session");
    }

    public Response loginAsAdmin(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "zhangchris280");
        jsonObject.put("password", "Zhn122737");

        return given(requestSpec)
                .when()
                .body(jsonObject.toString())
                .post();

    }

    public Response loginAsTeamLeader(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "John Doe");
        jsonObject.put("password", "password123");

        return given(requestSpec)
                .when()
                .body(jsonObject.toString())
                .post();

    }

    public Response loginAsDeveloper(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "Adam Smith");
        jsonObject.put("password", "password123");

        return given(requestSpec)
                .when()
                .body(jsonObject.toString())
                .post();
    }
    @Test
    public void test(){
        loginAsAdmin();
    }
}
