package apiTest.api.ChrisAPI;

import apiTest.api.BaseAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class IssueMgmtDeveloperAllIssuesAPI extends BaseAPI {
    public IssueMgmtDeveloperAllIssuesAPI() {
        requestSpec.basePath("/rest/api/2/search");
    }

    public Response getAllIssuesOnAUser(String userName) {
        Response response = given(requestSpec)
                .auth().preemptive().basic("Adam Smith","password123")
                .queryParam("jql","assignee='"+userName+"'")
                .when()
                .get();
//        response.then().statusCode(200);
//        response.then().log().body();
        return response;
    }

    @Test
    public void test(){
        getAllIssuesOnAUser("Adam Smith");
    }
}
