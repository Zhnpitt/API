package apiTest.api.ChrisAPI;

import apiTest.api.BaseAPI;
import apiTest.entities.Issue;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class IssueMgmtLeaderAPI extends BaseAPI {
    public IssueMgmtLeaderAPI() {
        requestSpec.basePath("/rest/api/2/issue");
    }

    public Response createIssue(String issueType, String priority,
                                String summary, String description){

//        JSONObject issuePayload = new JSONObject();
//        issuePayload.put("fields", new JSONObject()
//                .put("project", new JSONObject().put("key", "PROJ"))
//                .put("summary", summary)
//                .put("description", description)
//                .put("issuetype", new JSONObject().put("name", issueType))
//                .put("priority", new JSONObject().put("id", priority)));


        Issue issuePayload = Issue.builder()
                .fields(Issue.Fields.builder()
                        .project(Issue.Fields.Project.builder().key("PROJ").build())
                        .summary(summary)
                        .description(description)
                        .issuetype(Issue.Fields.Issuetype.builder().name(issueType).build())
                        .priority(Issue.Fields.Priority.builder().id(priority).build())
                        .build())
                .build();

        System.out.println("Request Payload: " + issuePayload.toString());
        Response response = given(requestSpec)
                .auth().preemptive().basic("John Doe","password123")
                .when()
                .body(issuePayload)
                .post();
        response.then().log().body();
        return response;
    }


    @Test
    public void test(){
//        createIssue("Story","3","Issue Summary", "Issue Description");
//        assignIssueToUser("PROJ-102", "Adam Smith");

        createIssue("Story","2","story1 by John", "story1 by John");
        createIssue("Task","3", "task1 by John" , "task1 by John");
        createIssue("Task","3","task2 by John","task2 by John");

    }


    public Response assignIssueToUser(String issueId, String username){
        JSONObject user = new JSONObject();
        user.put("name", username);
        Response response = given(requestSpec)
                .auth().preemptive().basic("John Doe","password123")
                .pathParams("issueIdOrKey", issueId)
                .when()
                .body(user.toString())
                .put("/{issueIdOrKey}/assignee");

        response.then().log().body();
        return  response;
    }



}
