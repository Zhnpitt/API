package apiTest.api.ChrisAPI;

import apiTest.api.BaseAPI;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GroupMgmtAPI extends BaseAPI {
    public GroupMgmtAPI(){
        requestSpec.basePath("/rest/api/2/group");
    }

    public Response createGroup(String groupName){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", groupName);

        Response response = given(requestSpec)
//                .cookies(cookies)
//                .cookies(LoginAPI.loginAsAdmin().getCookies())
                .auth().preemptive().basic("zhangchris280","Zhn122737")
                .when()
                .body(jsonObject.toString())
                .post();
//        response.then().log().body();
        return response;
    }

    public Response addUserToGroup(String groupName, String userName){
        JSONObject user = new JSONObject();
        user.put("name", userName);

        return given(requestSpec)
                .auth().preemptive().basic("zhangchris280","Zhn122737")
                .queryParam("groupname", groupName)
                .when()
                .body(user.toString())
                .post("/user");
    }

//    @Test
//    public void test(){
//        addUserToGroup("grp1", "Adam Smith");
//    }

}
