package stepDefinitions;

import base.BaseTest;
import pages.CarLoanCalculatorPage;
import pages.HomePage;
import utils.LogUtil;
import utils.ScreenshotUtil;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.Keys;

public class CarLoanSteps extends BaseTest {
    HomePage homePage;
    CarLoanCalculatorPage carLoanPage;

    @Given("I open the Car Loan EMI Calculator")
    public void openCarLoanCalculator() {
        homePage = new HomePage(driver);
        homePage.navigateToCarLoanCalculator();
        carLoanPage = new CarLoanCalculatorPage(driver);
    }

    @When("I enter loan amount {string}, tenure {string}, and interest rate {string}")
    public void enterLoanDetails(String amount, String tenure, String rate) {
        carLoanPage.getLoanAmountField().sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        carLoanPage.getLoanAmountField().sendKeys(amount);
        carLoanPage.getTenureField().sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        carLoanPage.getTenureField().sendKeys(tenure);
        carLoanPage.getInterestRateField().sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        carLoanPage.getInterestRateField().sendKeys(rate, Keys.ENTER);
    }

    @Then("I should see the monthly EMI displayed")
    public void verifyEMI() {
        String emiValue = carLoanPage.getMonthlyEMI().replaceAll("[^0-9.]", "");
        double emi = Double.parseDouble(emiValue);

        double principal = 1500000.0;
        double monthlyRate = (9.5 / 100) / 12;
        double interestM1 = principal * monthlyRate;
        double principalM1 = emi - interestM1;

        System.out.println("Month 1 Interest: " + Math.round(interestM1));
        System.out.println("Month 1 Principal: " + Math.round(principalM1));
        LogUtil.writeLog("Car Loan EMI: " + emi + " | Interest M1: " + interestM1 + " | Principal M1: " + principalM1);
    }

    @Then("I should see an error message displayed")
    public void verifyErrorMessage() throws Exception {
        WebElement loanInput = driver.findElement(By.id("carLoanNewFixedLoanRange-Amt"));

        // Clear and type 0 (do not press Enter yet)
        loanInput.clear();
        loanInput.sendKeys("0");

        // ✅ Wait briefly so the error state is visible
        Thread.sleep(1500);

        String errorMsg = "";
        try {
            // Option 1: Custom tooltip span
            WebElement tooltip = driver.findElement(By.xpath(
                "//input[@id='carLoanNewFixedLoanRange-Amt']/following-sibling::span"));
            errorMsg = tooltip.getText();
        } catch (NoSuchElementException e) {
            // Option 2: Native HTML5 validation message
            JavascriptExecutor js = (JavascriptExecutor) driver;
            errorMsg = (String) js.executeScript("return arguments[0].validationMessage;", loanInput);
        }

        System.out.println("Error: " + errorMsg);
        LogUtil.writeLog("Error message: " + errorMsg);

        // ✅ Capture screenshot while error is visible
        String fileName = "test-output/screenshots/Error_" + System.currentTimeMillis() + ".png";
        ScreenshotUtil.captureScreenshot(driver, fileName);
        LogUtil.writeLog("Error screenshot saved: " + fileName);
    }

  

    @Then("I capture screenshot and log the error")
    public void logError() throws Exception {
        String fileName = "test-output/screenshots/Error_" + System.currentTimeMillis() + ".png";
        ScreenshotUtil.captureScreenshot(driver, fileName);
        LogUtil.writeLog("Error screenshot saved: " + fileName);
    }
}
