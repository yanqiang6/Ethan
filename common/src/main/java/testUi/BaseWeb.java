package testUi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.io.IOException;
import java.util.*;

public class BaseWeb {
    public static WebDriver driver;

    /**
     * @title WebDriver初始化，并访问任意url
     * @Description background传入0时,不启动浏览器
     */
    public static void openUrl(String url, Integer background) throws IOException {
        String version="84";
        String chromeDriverPath =System.getProperty("user.dir")+"\\src\\main\\resources\\driver\\chrome\\"+version+"\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);//设置驱动的路径
        if (background == 0) {
            //设置参数
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");
            driver = new ChromeDriver(chromeOptions);
        } else {
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();//浏览器最大化
        driver.get(url);

    }

    //对元素进行点击操作
    public static void click(By by) {
        driver.findElement(by).click();
    }

    //在元素对应文本框输入内容
    public static void sendKeys(By by, String keys) {
        driver.findElement(by).sendKeys(keys);
    }
    //鼠标移动到元素上,触发下拉列表展示
    public static void moveToElement(By by) {
        WebElement element = driver.findElement(by);
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    //浏览器中切换tab页
    public static void getWindows(int i){
        ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(i));
    }

    //当前页面有多个元素时，需要使用findElements
    public static List<WebElement> getEleList(By element,By elements){
        return driver.findElement(element).findElements(elements);
    }

    //处理下拉选择框
    public static void selectEleByIndex(By by,int index){
        //定位到下拉框元素
        WebElement elementDown=driver.findElement(by);
        Select select=new Select(elementDown);
        sleep(100);
        select.selectByIndex(index);
    }

    //设置强制等待时间,单位为ms
    public static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}