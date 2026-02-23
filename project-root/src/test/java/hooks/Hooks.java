package hooks;

import io.cucumber.java.*;
import base.BaseTest;
import utils.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Hooks extends BaseTest {
    private static ExtentReports extent = ExtentManager.getInstance();

    @Before
    public void beforeScenario(Scenario scenario) throws Exception { // Added 'throws Exception'
        ExtentTest test = extent.createTest(scenario.getName())
                                .assignCategory(scenario.getSourceTagNames().toArray(new String[0]));
        ExtentTestManager.setTest(test);
        
        // This now matches the signature in your updated BaseTest
        BaseTest.initializeDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            // Wait 2 seconds before screenshot so error messages are visible
            Thread.sleep(2000);

            String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
            String filePath = "test-output/screenshots/" + safeName + ".png";
            
            // Capture screenshot
            ScreenshotUtil.captureScreenshot(driver, filePath);

            if (scenario.isFailed()) {
                ExtentTestManager.getTest().fail("Scenario failed: " + scenario.getName())
                    .addScreenCaptureFromPath("../screenshots/" + safeName + ".png");
            } else {
                ExtentTestManager.getTest().pass("Scenario passed")
                    .addScreenCaptureFromPath("../screenshots/" + safeName + ".png");
            }
        } catch (Exception e) {
            System.err.println("Error in After Hook: " + e.getMessage());
        }
    }

    @AfterAll
    public static void tearDownAll() {
        if (extent != null) {
            extent.flush();
        }
        BaseTest.tearDown();
    }
}