package ui;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseAPP {
    public static AndroidDriver driver;

    //设置隐式等待时间，方法参数是等待的最大时长，作用于全局，可放在Before中
    public static void implicitlyWait(int timeout){
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }
    //设置休眠时间
    public void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    //根据元素进行点击
    public static void click(By by){
        driver.findElement(by).click();
    }

    //根据坐标进行点击
    public static void tap(int x,int y){
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(x, y)).perform();
    }
    //向下滑动屏幕
    public static void swipeDown() {
        TouchAction action=new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int height = size.height;
        int width = size.width;
        Duration duration=Duration.ofMillis(500);
        action
                .longPress(PointOption.point(width / 2, height - 100))
                .moveTo(PointOption.point(width / 2, 100)).release().perform();
    }
    //判断元素是否存在
    public static boolean isElementExist(By by) {
        try {
            driver.findElement(by);
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    //尝试查找元素，如果不在会自动向下滚动
    public static void tryFindDown(By by){
        try {
            driver.findElement(by).click();
        }catch (Exception e){
            while(isElementExist(by)==false){
                swipeDown();
                if (isElementExist(by)==true){
                    driver.findElement(by).click();
                    break;
                }
            }
        }
    }

    //计算元素的中心位置
    public Map locationValue(WebElement element) {
        HashMap locationValue =new HashMap();
        Point start = element.getLocation();//获取元素的起始x和y坐标
        int startX = start.x;
        int startY = start.y;
        //元素的宽和高
        Dimension q =element.getSize();
        System.out.println(q.getWidth());
        int x =q.getWidth();
        int y = q.getHeight();
        //System.out.println("坐标轴差:X值为"+x+"Y值为"+y);

        //计算出元素的终点坐标
        int endX = x + startX;
        int endY = y + startY;
        //计算中间点坐标
        //System.out.println((endX + startX) / 2);
        //System.out.println((endY + startY-y/2) / 2);
        locationValue.put((endX + startX) / 2,(endY +startY-y/2) / 2);
        return locationValue;
    }

}
