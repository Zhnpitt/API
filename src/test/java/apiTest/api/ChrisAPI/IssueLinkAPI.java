package apiTest.api.ChrisAPI;

import apiTest.api.BaseAPI;
import apiTest.entities.IssueLink;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.dockerjava.api.model.Link;
import io.restassured.mapper.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class IssueLinkAPI extends BaseAPI {

    public IssueLinkAPI(){
        requestSpec.basePath("/rest/api/2/issueLink");
    }
    public Response createissueLinkBlocks(String issueLinkType, String inwardIssueKey,
                                          String outwardIssueKey) {
        IssueLink issueLink = IssueLink.builder()
                .type(IssueLink.Type.builder().name(issueLinkType).inward("is blocked by").outward("blocks").build())
                .inwardIssue(IssueLink.InwardIssue.builder().key(inwardIssueKey).build())
                .outwardIssue(IssueLink.OutwardIssue.builder().key(outwardIssueKey).build())
                .build();

        Response response = given(requestSpec)
                .auth().preemptive().basic("John Doe","password123")
                .when()
                .body(issueLink)
                .post();
        response.then().log().body();
        return response;
    }

    public Response createissueLinkToEpic(String link, String inwardIssueKey,
                                          String outwardIssueKey) {
        IssueLink issueLink = IssueLink.builder()
                .type(IssueLink.Type.builder().name(link).inward("is Epic of").outward("has Epic").build())
                .inwardIssue(IssueLink.InwardIssue.builder().key(inwardIssueKey).build())
                .outwardIssue(IssueLink.OutwardIssue.builder().key(outwardIssueKey).build())
                .build();

        Response response = given(requestSpec)
                .auth().preemptive().basic("John Doe","password123")
                .when()
                .body(issueLink)
                .post();
        response.then().log().body();
        return response;
    }


    public Response specifyIssueLinkType(String LinkId) {
        Response response = given(requestSpec)
                .auth().preemptive().basic("John Doe","password123")
                .when()
                .get("/"+ LinkId);
        response.then().statusCode(200);
        return response;
    }

    public String getLinkIdForIssue(String issueKey){
        Response response = given()
                .auth().preemptive().basic("John Doe","password123")
                .queryParam("jql","key="+issueKey)
                .when()
                .get("http://localhost:8080/rest/api/2/search");

        JsonPath jsonPath = response.jsonPath();
        List<String> issueLinkIds = jsonPath.getList("issues[0].fields.issuelinks.collect { it.id }");
//        System.out.println(issueLinkIds.get(0));
        return issueLinkIds.get(0);
    }

    @Test
    public void test(){
//        createissueLinkToEpic("Blocks", "PROJ-94", "PROJ-102");
//
        String linkId = getLinkIdForIssue("PROJ-94");
        specifyIssueLinkType(linkId);
    }
}
