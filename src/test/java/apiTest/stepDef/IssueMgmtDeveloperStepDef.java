package apiTest.stepDef;

import apiTest.api.ChrisAPI.IssueMgmtDeveloperAllIssuesAPI;
import apiTest.api.ChrisAPI.IssueMgmtDeveloperCommentAPI;
import apiTest.api.ChrisAPI.LoginAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class IssueMgmtDeveloperStepDef {

    private static final ThreadLocal<Response> currentResponse= new ThreadLocal<>();

    LoginAPI loginAPI = new LoginAPI();

    IssueMgmtDeveloperCommentAPI issueMgmtDeveloperCommentAPI = new IssueMgmtDeveloperCommentAPI();
    IssueMgmtDeveloperAllIssuesAPI issueMgmtDeveloperAllIssuesAPI = new IssueMgmtDeveloperAllIssuesAPI();

    @Given("I login in as a developer user")
    public void iLoginInAsADeveloperUser() {
        currentResponse.set(loginAPI.loginAsDeveloper());
        currentResponse.get().then().statusCode(200);
    }


    @When("I check all issues on me {string}")
    public void iCheckAllIssuesOnMe(String username) {
        currentResponse.set(issueMgmtDeveloperAllIssuesAPI.getAllIssuesOnAUser(username));
    }

    @Then("I verify the status code of checking being {int}")
    public void iVerifyTheStatusCodeOfCheckingBeing(int code) {
        currentResponse.get().then().statusCode(code);
    }

    @When("I add a message {string} to an issue {string}")
    public void iAddAMessageToAnIssue(String message, String issueKey) {

        currentResponse.set(issueMgmtDeveloperCommentAPI.addCommentToAnIssue(message, issueKey));
    }

    @Then("I verify the status code of adding message being {int}")
    public void iVerifyTheStatusCodeOfAddingMessageBeing(int code) {
        currentResponse.get().then().statusCode(code);
    }

    @When("I edit a message {string} to an issue {string}")
    public void iEditAMessageToAnIssue(String editedMsg, String issueKey) {
        String commentId = issueMgmtDeveloperCommentAPI.getAllCommentsOfAnIssue(issueKey);
        currentResponse.set(issueMgmtDeveloperCommentAPI.editCommentOfAnIssue(editedMsg, issueKey, commentId));
    }

    @Then("I verify the status code of editing message being {int}")
    public void iVerifyTheStatusCodeOfEditingMessageBeing(int code) {
        currentResponse.get().then().statusCode(code);
    }
}
