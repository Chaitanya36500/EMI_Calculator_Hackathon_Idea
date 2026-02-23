package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class HomeLoanCalculatorPage {
    WebDriver driver;

    @FindBy(name="loan_amt")
    WebElement loanAmount;

    @FindBy(name="loan_tenure")
    WebElement tenure;

    @FindBy(name="interest_rate")
    WebElement interestRate;

    @FindBy(xpath="//p[@class='emi_amt']")
    WebElement emiAmount;

    // ✅ Updated Locator to find rows specifically within the Amortisation table body
    @FindBy(xpath="//table//tbody/tr")
    List<WebElement> scheduleRows;

    public HomeLoanCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterLoanDetails(String amount, String tenureValue, String rate) {
        loanAmount.clear();
        loanAmount.sendKeys(amount);
        tenure.clear();
        tenure.sendKeys(tenureValue);
        interestRate.clear();
        interestRate.sendKeys(rate, Keys.ENTER);
    }

    public String getMonthlyEMI() {
        return emiAmount.getText();
        
    }

    // ✅ Updated to capture headers and all available rows
    public List<String[]> extractYearlyTableData() {
        List<String[]> dataList = new ArrayList<>();
        
        // Add Header row manually if not easily scrapable
        dataList.add(new String[]{"Year", "Opening Balance", "EMI*12", "Interest paid yearly", "Principal paid yearly", "Closing Balance"});

        for (WebElement row : scheduleRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (!cells.isEmpty()) {
                String[] rowData = new String[cells.size()];
                for (int i = 0; i < cells.size(); i++) {
                    rowData[i] = cells.get(i).getText();
                }
                dataList.add(rowData);
            }
        }
        return dataList;
    }
}