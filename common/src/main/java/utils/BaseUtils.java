package utils;

import api.model.ConfigModel;
import com.alibaba.fastjson.JSON;
import io.restassured.response.Response;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;


/**
 * @version 1.0
 * @Author Ethan
 * @Date 2020/8/23 19:37 Create
 */
public class BaseUtils {
    //获取当前工程所在目录下文件的绝对路径
    public String getPath(String path){
        return System.getProperty("user.dir")+path;
    }

    //yaml方式加载配置文件:获取当前环境对应的账号和密码
    public HashMap<String,String> readAccountInfo(String path, String currentEnv) throws IOException {
        ConfigModel configModel=new ConfigModel();
        //根据当前环境，获取环境对应的账号和密码
        HashMap<String,String> accountInfo= configModel.load(path).env.get(currentEnv);
//        for (Map.Entry<String,String > info:accountInfo.entrySet()){
//            System.out.println(info.getKey()+":"+info.getValue());
//        }
        return accountInfo;
    }
    //文本方式加载配置文件:根据key获取对应的value值
    public String readConfig(String path,String key) throws IOException {
        Properties pro = new Properties();
        Properties savePro = new Properties();
        pro.load(new FileInputStream(path));
        return pro.getProperty(key);
    }
    //使用poi读取Excel文件,某个sheet页
    public Sheet loadExcel(String path,int n) throws Exception{
        File inputFile=new File(path);
        //使用字符流去接File的数据
         FileInputStream inputStream=new FileInputStream(inputFile);
        //workbook去接fileInputStream
        Workbook workbook= WorkbookFactory.create(inputStream);
        //读取到了excel文件，但是需要去判断是哪一个工作簿，要用到Sheet类
        Sheet sheet = workbook.getSheetAt(n);
        return sheet;
    }
    //读取Excel文件,某行某列的值
    public void getExcelValue(String path,int sheetNum,int x,int y) throws Exception {
        Sheet currentSheet=loadExcel(path,sheetNum);
        if(x>0 && y>0){
            x-=1;//row照正常输入，需要-1
            y-=1;//cell按照正常输入，需要-1
            Row sheetRow=currentSheet.getRow(x);
            System.out.println(sheetRow.getCell(y).getStringCellValue());
        }else {
            System.out.println("输入有误，请输入大于0的正整数");
        }

    }

    //调用企业微信机器人,发送push消息
    public void sendMessage(String url,String content) {
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> textMap = new HashMap<>();
        dataMap.put("msgtype", "text");
        textMap.put("content", content);
        textMap.put("mentioned_list", Arrays.asList("@all"));
        dataMap.put("text", textMap);
        String jsonData = JSON.toJSONString(dataMap);
        Response res =
                given().
                        contentType("application/json;charset=UTF-8").
                        body(jsonData).
                        when()
                        .post(url);
    }

}
