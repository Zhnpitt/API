package apiTest.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;
import apiTest.drivers.DriverFactory;

@CucumberOptions(
        features = {"src/test/resources/feature/api/DeveloperIssueMgmt.feature"
        },
        glue = {"apiTest.stepDef"}
)
public class IssueMgmtDeveloperTestRunner extends AbstractTestNGCucumberTests{
        @AfterTest
        public void quitBrowser() {
            DriverFactory.teardown();
        }
}
