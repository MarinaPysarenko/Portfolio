import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import org.junit.*;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testOfOrder {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private Select select;


    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        //Url дева
       // baseUrl = "http://jh3.php-cd.attractgroup.com/";

         baseUrl = "https://www.jandh.com/";
         driver.manage().window().maximize();
    }


    public Select getSelect(WebElement element) {
        select = new Select(element);
        return select;
    }
    @Test
    public void testOfCart() throws Exception {
        driver.get(baseUrl);
        /*Переход на страничку товара*/

        //товар на деве
        //WebElement element = driver.findElement(By.xpath("(//img[@alt='Daiwa Procyon EX PREX2500SH Spinning Reel'])[2]"));

        //товар на проде
        WebElement element = driver.findElement(By.xpath("(//img[@alt='Shimano Tranx 300 and 400 Low Profile Reels REV'])"));

        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(200);
        actions.moveToElement(element).click().perform();
        Thread.sleep(200);


        //Добавление товара в корзину на деве
       /*driver.findElement(By.xpath("//button[@type='button']")).click();
        driver.findElement(By.id("popup_confirm_cancel")).click();
        Thread.sleep(500);*/


        //Добавление товара на проде в корзину
        driver.findElement(By.xpath(".//*[@id='product_button_add_cart']/button")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath(".//*[@id='popup_confirm_cancel']")).click();
        Thread.sleep(500);


        //тестовый чекаут
       // driver.get("http://jh3.php-cd.attractgroup.com/checkout/edit?test=1");


        //тестовый прод
        driver.get("https://www.jandh.com/checkout/edit?test=1");

        //чекаут
        driver.findElement(By.name("bill_first_name")).click();
        driver.findElement(By.name("bill_first_name")).sendKeys("tst");
        driver.findElement(By.name("bill_last_name")).click();
        driver.findElement(By.name("bill_last_name")).sendKeys("tet");
        driver.findElement(By.name("bill_company")).click();
        driver.findElement(By.name("bill_company")).sendKeys("test");
        driver.findElement(By.name("bill_phone_number")).click();
        driver.findElement(By.name("bill_phone_number")).sendKeys("0123456789");
        driver.findElement(By.name("bill_address1")).click();
        driver.findElement(By.name("bill_address1")).sendKeys("ertret");
        driver.findElement(By.name("bill_address2")).click();
        driver.findElement(By.name("bill_address2")).sendKeys("ertret");
        driver.findElement(By.name("bill_zip_code")).click();
        driver.findElement(By.name("bill_zip_code")).sendKeys("20020");
        driver.findElement(By.xpath("//form[@id='checkout_edit_form']/div/div[2]/div[2]")).click();
        driver.findElement(By.name("card_number")).click();
        driver.findElement(By.name("card_number")).sendKeys("4111111111111111");
        Thread.sleep(500);

        //отображает скрытое поле месяцы,и устанавливает значение 4 месяца

        WebElement numberOfMonth= driver.findElement(By.xpath(".//*[@id='checkout_edit_form']//tr[1]/td[2]/div/select"));
        String nm = "arguments[0].setAttribute('style','visibility:visible;');";
        ((JavascriptExecutor) driver).executeScript(nm, numberOfMonth);

        Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='checkout_edit_form']//tr[1]/td[2]/div/select")));
        dropdown.selectByValue("04");

        //отображает год и устанавливает значение  года
        WebElement numberOfYear = driver.findElement(By.xpath(".//*[@id='checkout_edit_form']//tr[1]/td[3]/div/select"));
        String ny = "arguments[0].setAttribute('style','visibility:visible;');";
        ((JavascriptExecutor) driver).executeScript(ny, numberOfYear);

        Select dropdownOfYear = new Select(driver.findElement(By.xpath(".//*[@id='checkout_edit_form']//tr[1]/td[3]/div/select")));
        dropdownOfYear.selectByValue("2020");

        driver.findElement(By.xpath(".//*[@id='checkout_edit_form']//tr[2]/td[2]/div/input")).click();
        driver.findElement(By.xpath(".//*[@id='checkout_edit_form']//tr[2]/td[2]/div/input")).sendKeys("111");
        driver.findElement(By.xpath(".//*[@id='checkout_edit_form']//div[14]/input")).click();
        driver.findElement(By.xpath(".//*[@id='checkout_edit_form']//div[14]/input")).sendKeys("zagatap@p33.org");
        Thread.sleep(500);

        //Кнопка "Review"
        driver.findElement(By.xpath(".//*[@id='review_your_order_btn']")).click();
        Thread.sleep(1000);

        //Кнопка "Place your order"
         driver.findElement(By.xpath("//div[1]/form/button")).click();
        Thread.sleep(1000);

            //Получения номера заказа
        String numberOfOrder = driver.findElement(By.className("order_num")).getText();
        System.out.println(numberOfOrder);
        //Проверка на успешный заказ
        assertTrue(numberOfOrder.contains("Order Number"));


    }


/*
       @After
        public void tearDown() throws Exception {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }
*/
    }






