package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarLoanCalculatorPage {
    WebDriver driver;

    @FindBy(id="carLoanNewFixedLoanRange-Amt")
    private WebElement loanAmount;

    @FindBy(name="loan_tenure")
    private WebElement tenure;

    @FindBy(name="interest_rate")
    private WebElement interestRate;

    @FindBy(xpath="//p[@class='emi_amt']")
    private WebElement emiAmount;

    // ✅ Locator for the Slider Handle based on your HTML
    @FindBy(css=".noUi-handle")
    private WebElement loanSliderHandle;

    public CarLoanCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getLoanAmountField() {
        return loanAmount;
    }

    public WebElement getTenureField() {
        return tenure;
    }

    public WebElement getInterestRateField() {
        return interestRate;
    }

    public String getMonthlyEMI() {
        return emiAmount.getText();
    }

    /**
     * ✅ UI Validation Requirement: Move the slider (scroll bar)
     * This moves the handle to simulate user interaction with the scale.
     */
    public void moveLoanSlider(int xOffset) {
        Actions move = new Actions(driver);
        // Drag the handle by a specific horizontal offset
        move.dragAndDropBy(loanSliderHandle, xOffset, 0).build().perform();
    }
}