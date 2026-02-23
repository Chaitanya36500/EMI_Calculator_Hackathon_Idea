// HomePage.java
package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    @FindBy(xpath = "//a[@title='Discover Products']") WebElement discoverProducts;
    @FindBy(xpath = "//a[@title='Calculator']") WebElement calculatorMenu;
    @FindBy(xpath = "//a[@title='Home Loan EMI Calculator']") WebElement homeLoanCalculator;
    @FindBy(xpath = "//a[@title='Car Loan EMI - New Calculator']") WebElement carLoanCalculator;

    public HomePage(WebDriver driver) { this.driver = driver; PageFactory.initElements(driver, this); }
    public void navigateToCarLoanCalculator() { discoverProducts.click(); calculatorMenu.click(); carLoanCalculator.click(); }
    public void navigateToHomeLoanCalculator() { discoverProducts.click(); calculatorMenu.click(); homeLoanCalculator.click(); }
}