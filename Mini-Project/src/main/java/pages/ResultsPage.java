package pages;

public class ResultsPage extends BaseTest {
    @FindBy(className = "search-results")
    List<WebElement> results;

    @FindBy(id = "ddlSort") // Dropdown ID
    WebElement sortDropdown;

    @FindBy(xpath = "//div[@class='title']//a")
    List<WebElement> bookNames;

    @FindBy(xpath = "//div[@class='price']//span")
    List<WebElement> bookPrices;

    public ResultsPage() {
        PageFactory.initElements(driver, this);
    }

    public int getResultsCount() {
        return results.size();
    }

    public void sortByPriceLowToHigh() {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Price - Low to High");
    }

    public void printFirstFiveResults() {
        for (int i = 0; i < 5 && i < bookNames.size(); i++) {
            System.out.println("Book: " + bookNames.get(i).getText() + " | Price: " + bookPrices.get(i).getText());
        }
    }
}
