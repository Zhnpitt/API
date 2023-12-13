package apiTest.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;
import apiTest.drivers.DriverFactory;

@CucumberOptions(
        features = {"src/test/resources/feature/api/teamLeaderIssueMgmt.feature"
        },
        glue = {"apiTest.stepDef"}
)
public class IssueMgmtLeaderTestRunner extends AbstractTestNGCucumberTests {
    @AfterTest
    public void quitBrowser() {
        System.out.println();
        DriverFactory.teardown();
    }
}
