package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.ArrayList;

public class BaseWeb {
    public static WebDriver driver;

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

    /**
     * 方法说明:通用访问任意url的方法
     * 参数说明:
     * background传入0时,不启动浏览器
     */
    public static void login(String url, Integer background) throws IOException {

    }
}