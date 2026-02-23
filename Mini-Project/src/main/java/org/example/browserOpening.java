package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class browserOpening {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.bookswagon.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("inputbar")).sendKeys("Selenium WebDriver");
        driver.findElement(By.id("btnTopSearch")).click();
        String result = driver.findElement(By.cssSelector("div[class='preferences-show'] b")).getText();
        int result1 = Integer.parseInt(result);
        boolean state_result = (result1>10);
        System.out.println(result);
        System.out.println(state_result);
        WebElement dropdownResult = driver.findElement(By.id("ddlSort"));
        Select sort = new Select(dropdownResult);
        sort.selectByValue("Product_ActualPrice asc");
        List<WebElement> products = driver.findElements(By.cssSelector("div.product"));

        for (int i = 0; i < Math.min(5, products.size()); i++) {
            WebElement product = products.get(i);

            // Get book title from <div class="title"><a>...</a></div>
            String title = product.findElement(By.cssSelector("div.title > a")).getText();

            // Get selling price from <div class="sell">
            String price = product.findElement(By.cssSelector("div.price-attrib div.price div.sell")).getText();

            System.out.println("Title: " + title + ", Price: " + price);
        }
        Thread.sleep(10000);
        driver.close();
    }
}
