package apiTest.api.ChrisAPI;

import apiTest.api.BaseAPI;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class IssueMgmtDeveloperCommentAPI extends BaseAPI {
    public IssueMgmtDeveloperCommentAPI(){
        requestSpec.basePath("/rest/api/2/issue");
    }

    public Response addCommentToAnIssue(String message, String issueId){
        JSONObject comment = new JSONObject();
        comment.put("body",message);
        System.out.println(comment.toString());
        System.out.println(issueId);

        Response response = given()
                .contentType(ContentType.JSON)
                .auth().preemptive().basic("Adam Smith","password123")
                .pathParams("issueIdOrKey", issueId)
                .when()
                .body(comment.toString())
                .post("http://localhost:8080/rest/api/2/issue/{issueIdOrKey}/comment");
//        response.then().log().body();
//        response.then().statusCode(201);
        return  response;
    }

    public Response editCommentOfAnIssue(String editedMsg, String issueId, String commentId){
        JSONObject editedComment = new JSONObject();
        editedComment.put("body", editedMsg);

        System.out.println("Request Payload: " + editedComment.toString());

        Response response = given(requestSpec)
                .auth().preemptive().basic("Adam Smith","password123")
                .pathParams("issueIdOrKey", issueId)
                .pathParams("id", commentId)
                .when()
                .body(editedComment.toString())
                .put("/{issueIdOrKey}/comment/{id}");

//
        response.then().log().body();
        return response;
    }

    public String getAllCommentsOfAnIssue(String issueId){
        Response response = given(requestSpec)
                .auth().preemptive().basic("Adam Smith","password123")
                .pathParams("issueIdOrKey", issueId)
                .when()
                .get("/{issueIdOrKey}/comment");

        response.then().statusCode(200);


        JsonPath jsonPath = response.jsonPath();
        List<String> commentIds = jsonPath.getList("comments.id");
        commentIds.forEach( System.out::println);
        System.out.println(commentIds.get(0));
        return commentIds.get(0);

    }

    @Test
    public void test(){
//        Response response = addCommentToAnIssue("message add on 12 11", "PROJ-5");
//        response.then().log().body();
//        response.then().statusCode(201);
        String commentId = getAllCommentsOfAnIssue("PROJ-5");
        editCommentOfAnIssue("23.57:34+","PROJ-5", commentId);
    }
}
