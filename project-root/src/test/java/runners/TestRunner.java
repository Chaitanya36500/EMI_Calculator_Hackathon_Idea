package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",
    glue = {"stepDefinitions", "hooks"},
    plugin = {
        "pretty", 8
        "html:test-output/cucumber-report.html",
        "rerun:target/rerun.txt" // ✅ Tracks failures
    },
    monochrome = true
)
public class TestRunner { }