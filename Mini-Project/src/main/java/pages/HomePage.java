package pages;

public class HomePage extends BaseTest {
    @FindBy(id = "inputbar") // Using ID locator
    WebElement searchBox;

    @FindBy(name = "btnSearch") // Using Name locator
    WebElement searchBtn;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public void searchBook(String bookName) {
        searchBox.sendKeys(bookName);
        searchBtn.click();
    }
}