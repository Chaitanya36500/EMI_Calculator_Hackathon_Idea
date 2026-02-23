package stepDefinitions;

import base.BaseTest;
import pages.HomePage;
import pages.HomeLoanCalculatorPage;
import utils.ExcelUtil;
import utils.LogUtil;
import io.cucumber.java.en.*;
import java.util.List;

public class HomeLoanSteps extends BaseTest {
    HomePage homePage;
    HomeLoanCalculatorPage homeLoanPage;

    @Given("I open the Home Loan EMI Calculator")
    public void openHomeLoanCalculator() {
        homePage = new HomePage(driver);
        homePage.navigateToHomeLoanCalculator();
        homeLoanPage = new HomeLoanCalculatorPage(driver);
    }

    @When("I enter home loan amount {string}, tenure {string}, and interest rate {string}")
    public void enterHomeLoanDetails(String amount, String tenure, String rate) {
        homeLoanPage.enterLoanDetails(amount, tenure, rate);
    }

    @Then("I should see the monthly EMI for home loan displayed")
    public void verifyHomeLoanEMI() {
        String emi = homeLoanPage.getMonthlyEMI();
        System.out.println("Home Loan EMI: " + emi);
        LogUtil.writeLog("Home Loan EMI: " + emi);
    }

    @Then("I should store EMI details in Excel")
    public void storeEMIInExcel() throws Exception {
        List<String[]> tableData = homeLoanPage.extractYearlyTableData();
        ExcelUtil.writeFullTable("HomeLoanData", tableData);
        LogUtil.writeLog("Home Loan amortization table stored in Excel.");
    }
}
