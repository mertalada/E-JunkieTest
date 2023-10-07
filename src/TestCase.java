import Utility.BaseDriver;
import Utility.MyFunction;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class TestCase extends BaseDriver {
    @Test(priority = 1)
    public void Ebook_Payment_Process(){
        WebElement addToCart= driver.findElement(By.xpath("//a[text()='Add to Cart']"));
        addToCart.click();

        driver.switchTo().frame(0);

        MyFunction.Wait(1);
        WebElement debitCard = driver.findElement(By.cssSelector("[data-option='CC']"));
        debitCard.click();

        MyFunction.Wait(1);
        WebElement paying = driver.findElement(By.xpath("//*[text()='Pay 0.01 USD']"));
        paying.click();

        MyFunction.Wait(1);
        WebElement invalidEmail = driver.findElement(By.xpath("//span[text()='Invalid Email']"));

        Assert.assertTrue(invalidEmail.getText().contains("Invalid Email"));

    }
    @Test(priority = 2)
    public void  Payment_and_Confirmation_Credit_Card(){
        driver.get("https://e-junkie.com/wiki/demo/");
        MyFunction.Wait(1);
        Actions aksiyonlar = new Actions(driver);

        WebElement addTOCart = driver.findElement(By.xpath("//a[text()='Add to Cart']"));
        addTOCart.click();

        driver.switchTo().frame(0);

        MyFunction.Wait(1);
        WebElement debitCard = driver.findElement(By.cssSelector("[data-option='CC']"));
        debitCard.click();

        MyFunction.Wait(1);
        WebElement email = driver.findElement(By.xpath("//input[@type='email' and @placeholder='Email']"));
        email.sendKeys("testingteam4@outloo.com");


        aksiyonlar.sendKeys(Keys.TAB).sendKeys("testingteam4@outloo.com").build().perform();
        aksiyonlar.sendKeys(Keys.TAB).sendKeys("Team").build().perform();
        aksiyonlar.sendKeys(Keys.TAB).sendKeys("+905435434343").build().perform();
        aksiyonlar.sendKeys(Keys.TAB).sendKeys("TeamDört").build().perform();
        aksiyonlar.sendKeys(Keys.TAB).sendKeys("Atatürk cd 200/10 sk ").build().perform();
        aksiyonlar.sendKeys(Keys.TAB).sendKeys("Turkey").build().perform();
        aksiyonlar.sendKeys(Keys.TAB).sendKeys("İzmir").build().perform();
        aksiyonlar.sendKeys(Keys.TAB).sendKeys("Turkey").build().perform();

        WebElement city= driver.findElement(By.cssSelector("div.Billing-Form.Form > p.Billing-City.Inline.MarginRight > input[type=text]"));
        city.sendKeys("İzmir");

        WebElement code= driver.findElement(By.cssSelector("div.Billing-Form.Form > p.Billing-PostCode.Inline > input[type=text]"));
        code.sendKeys("35000");


        WebElement cardNo= driver.findElement(By.cssSelector("div.Checkout-Options > div:nth-child(1) > p.Card-Number > input[type=tel]"));
        cardNo.sendKeys("4242 4242 4242 4242");


        WebElement My= driver.findElement(By.cssSelector("div.Checkout-Options > div:nth-child(1) > p.Card-Expiry.Inline.MarginRight > input[type=tel]"));
        My.sendKeys(" 12/23");


        WebElement cvv= driver.findElement(By.cssSelector("div.Checkout-Options > div:nth-child(1) > p.Card-CVV.Inline > input[type=tel]"));
        cvv.sendKeys("315");


        MyFunction.Wait(1);
        WebElement pay= driver.findElement(By.cssSelector("div.column.one-half.RightColumn > div.Checkout-Options > button.Pay-Button"));
        aksiyonlar.moveToElement(pay).click().build().perform();

        WebDriverWait bekle = new WebDriverWait(driver, Duration.ofSeconds(20));
        bekle.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=' your order is confirmed. Thank you!']")));
        WebElement complete = driver.findElement(By.xpath("//*[text()=' your order is confirmed. Thank you!']"));

        Assert.assertTrue(complete.getText().contains("your order is confirmed"), "Siparişiniz tamamlanmadı");


    }
}
