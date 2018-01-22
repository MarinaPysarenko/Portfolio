import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.junit.*;



import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class testOfCart {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "http://jh3.php-cd.attractgroup.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testOfCart() throws Exception {
        driver.get(baseUrl);
        /*Переход на страничку товара*/
        WebElement element = driver.findElement(By.xpath("(//img[@alt='Daiwa Procyon EX PREX2500SH Spinning Reel'])[2]"));
        Thread.sleep(1000);

        Actions actions = new Actions(driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(200);
        actions.moveToElement(element).click().perform();
        Thread.sleep(200);

        //Получение цены товара
        String price = driver.findElement(By.xpath(".//*[@id='product_price']/span[2]/span[1]")).getText();


        //Добавление товара в корзину
        driver.findElement(By.xpath("//button[@type='button']")).click();
        driver.findElement(By.id("popup_confirm_cancel")).click();
        Thread.sleep(500);

        //Проверка на то,что товар добавлен в корзину
        assertEquals("Product is not present at the cart", 1, driver.findElements(By.cssSelector(".amount")).size());
        Thread.sleep(1000);


        driver.findElement(By.linkText("Change")).click();
        driver.findElement(By.cssSelector("input.new_amount")).clear();
        driver.findElement(By.cssSelector("input.new_amount")).sendKeys("2");
        driver.findElement(By.linkText("save")).click();
        Thread.sleep(5000);

        //Получение числа из строки (количество товара)
        String result = driver.findElement(By.cssSelector(".amount")).getText().split("")[0];
        int number = Integer.parseInt(result);

        //Проверка на изменения количества товара в корзине
        assertEquals("The quantity of the item is changed", 2, number);
        //получение цены товара, получение количества товара
        double itemPrice = Double.parseDouble(price);
        String sizeQuantity = driver.findElement(By.cssSelector(".amount")).getText();
        int quantity = Integer.parseInt(sizeQuantity);

        //Сумма товара
        DecimalFormat df = new DecimalFormat("####0.00");
        double total = itemPrice * quantity;

        //Получить subtotal
        String subtotal = driver.findElement(By.cssSelector(".price.subtotal_total_price")).getText();
        String extracted = subtotal.substring(subtotal.indexOf("$") + 1);

        // Перевожу subtotal в double
        double subtot = Double.parseDouble(extracted);
        assertEquals(df.format(total), df.format(subtot));

        //Удаление товаров из корзины
        driver.findElement(By.linkText("Delete")).click();
        driver.findElement(By.id("popup_confirm_ok")).click();
        Thread.sleep(2000);

        //получаю количетсво товара в корзине
        String deleteCart = driver.findElement(By.cssSelector("#top_cart_count")).getText();
        int numberOfDeleteCart = Integer.parseInt(deleteCart);

        //проверка есть ли товар после удаления в корзине
        assertEquals("Product is not deleted from the cart", 0, numberOfDeleteCart);

        //Нажимаем на лого
        driver.findElement(By.cssSelector("img.logo")).click();


    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}


