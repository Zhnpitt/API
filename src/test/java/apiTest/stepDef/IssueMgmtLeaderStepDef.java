package apiTest.stepDef;

import apiTest.api.ChrisAPI.IssueLinkAPI;
import apiTest.api.ChrisAPI.IssueMgmtLeaderAPI;
import apiTest.api.ChrisAPI.LoginAPI;
import apiTest.entities.Issue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class IssueMgmtLeaderStepDef {
    private static final ThreadLocal<Response> currentResponse= new ThreadLocal<>();
    Response response;
    LoginAPI loginAPI = new LoginAPI();
    IssueLinkAPI issueLinkAPI = new IssueLinkAPI();
    IssueMgmtLeaderAPI issueMgmtLeaderAPI = new IssueMgmtLeaderAPI();

    @Given("I login in as a team leader")
    public void iLoginInAsATeamLeader() {
        currentResponse.set(loginAPI.loginAsTeamLeader());
        currentResponse.get().then().statusCode(200);
    }

    @When("I Create issues with {string} and {string} and {string} and {string}")
    public void iCreateIssuesWithIssueTypeAndPriorityAndSummaryAndDescription(String issueType, String priority,
                                                                              String summary, String description) {

        response = issueMgmtLeaderAPI.createIssue(issueType, priority, summary, description);

    }
    @Then("I verify the status code of creating issue {int}")
    public void iVerifyTheStatusCodeOfCreatingIssue(int code) {
        response.then().statusCode(201);
    }

    @When("I link {string} the current issue {string} to a specific {string}")
    public void iLinkLinkTheCurrentIssueIssueKeyToASpecificEpicKey(String link,String issueKey ,
                                                                   String epicKey) {
        currentResponse.set(issueLinkAPI.createissueLinkToEpic(link, epicKey, issueKey));
    }

    @Then("I verify the status code of linking issue {int}")
    public void iVerifyTheStatusCodeOfLinkingIssue(int code) {
        currentResponse.get().then().statusCode(code);
    }

    @When("I create a {string} link between issue {string} and issue {string}")
    public void iCreateALinkBetweenIssueAndIssue(String Blocks, String issue1, String issue2) {
        currentResponse.set(issueLinkAPI.createissueLinkBlocks(Blocks, issue1, issue2));

    }

    @Then("I verify the status code of creating a Blocks link being {int}")
    public void iVerifyTheStatusCodeOfCreatingALinkBeing(int code) {
        currentResponse.get().then().statusCode(code);
    }

    @When("I check the link between issue {string} and issue {string}")
    public void iCheckTheLinkBetweenIssueAndIssue(String issue1, String issue2) {
        String linkId = issueLinkAPI.getLinkIdForIssue(issue1);
        currentResponse.set(issueLinkAPI.specifyIssueLinkType(linkId));
    }

    @Then("I verify the status code of link between issues being {int}")
    public void iVerifyTheStatusCodeOfLinkBetweenIssuesBeing(int code) {
        currentResponse.get().then().statusCode(code);
    }

    @When("I assign issue {string} to {string}")
    public void iAssignIssueIssueKeyToUser(String issueKey, String user ) {
        currentResponse.set(issueMgmtLeaderAPI.assignIssueToUser(issueKey, user));
    }

    @Then("I verify the status code of assigning being {int}")
    public void iVerifyTheStatusCodeOfAssigningBeing(int code) {
        currentResponse.get().then().statusCode(code);
    }


}
