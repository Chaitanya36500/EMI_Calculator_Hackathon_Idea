package stepDefinitions;

import base.BaseTest;
import pages.CarLoanCalculatorPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class LoanValidationSteps extends BaseTest {
    CarLoanCalculatorPage carLoanPage;

    @Given("I open the Loan Calculator")
    public void openLoanCalculator() {
        carLoanPage = new CarLoanCalculatorPage(driver);
    }

    @Then("I validate loan amount, tenure and interest fields are displayed")
    public void validateFields() {
        Assert.assertTrue(carLoanPage.getLoanAmountField().isDisplayed());
        Assert.assertTrue(carLoanPage.getTenureField().isDisplayed());
        Assert.assertTrue(carLoanPage.getInterestRateField().isDisplayed());
    }

    @Then("I move the loan slider")
    public void moveSlider() {
        carLoanPage.moveLoanSlider(50); // example offset
    }
}
