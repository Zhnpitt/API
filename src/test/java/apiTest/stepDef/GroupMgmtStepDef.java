package apiTest.stepDef;

import apiTest.api.ChrisAPI.GroupMgmtAPI;
import apiTest.api.ChrisAPI.LoginAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;

public class GroupMgmtStepDef {
    private static final ThreadLocal<Response> currentResponse= new ThreadLocal<>();

    LoginAPI loginAPI = new LoginAPI();

    GroupMgmtAPI groupMgmtAPI = new GroupMgmtAPI();
    @Given("I login in as an admin")
    public void iLoginInAsAnAdmin(){
        currentResponse.set(loginAPI.loginAsAdmin());
        currentResponse.get().then().statusCode(200);
    }

    @When("I create new {string} group")
    public void iCreateNewGroupGroupName(String groupName) {
        currentResponse.set(groupMgmtAPI.createGroup(groupName));
    }

    @Then("I verify the status code of creating group being {int}")
    public void iVerifyTheStatusCodeOfCreatingGroupBeing(Integer statusCode) {
        currentResponse.get().then().statusCode(statusCode);
    }

    @When("I assign user {} to group {}")
    public void iAssignUserUserNameToGroupGroup(String userName, String groupName) {
        currentResponse.set(groupMgmtAPI.addUserToGroup(groupName,userName));
    }

    @Then("I verify the status code of adding user to group being {int}")
    public void iVerifyTheStatusCodeOfAddingUserToGroupBeing(Integer statusCode) {
        currentResponse.get().then().statusCode(statusCode);
    }
}
