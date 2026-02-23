package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "@target/rerun.txt", // Note the '@' symbol
    glue = {"stepDefinitions", "hooks"},
    plugin = {
        "pretty", 
        "html:test-output/rerun-report.html"
    }
)
public class FailedTestRunner {
    // Leave empty
}