package utils;

import baseApi.model.ConfigModel;
import com.alibaba.fastjson.JSON;
import io.restassured.response.Response;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;

import java.util.*;

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
        HashMap<String,String> accountInfo= configModel.load(path).env.get(currentEnv);//根据当前环境，获取环境对应的账号和密码
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

    //将map保存存到resources的指定路径
    public void saveFile(HashMap<String,String> saveMaps,String savePath,Boolean append) throws IOException {
        FileOutputStream outFile=new FileOutputStream(savePath,append);//true表示追加打开,false会覆盖
        Properties pro=new Properties();
        //使用Iterator遍历HashMap
        Iterator it=saveMaps.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry) it.next() ;
            String key = entry.getKey().toString() ;
            String value = entry.getValue().toString() ;
            pro.setProperty(key,value);
        }
        pro.store(outFile,"save");
    }

    //使用poi读取Excel文件,某个sheet页
    public Sheet loadExcel(String path,int n) throws Exception{
        File inputFile=new File(path);
        FileInputStream inputStream=new FileInputStream(inputFile); //使用字符流去接File的数据
        Workbook workbook= WorkbookFactory.create(inputStream); //workbook去接fileInputStream
        Sheet sheet = workbook.getSheetAt(n); //读取到了excel文件，但是需要去判断是哪一个工作簿，要用到Sheet类
        return sheet;
    }
    //读取Excel文件,某行某列的值
    public void getExcelValue(String path,int sheetNum,int x,int y) throws Exception {
        Sheet currentSheet=loadExcel(path,sheetNum);
        if(x>0 && y>0){
            x-=1; //row照正常输入，需要-1
            y-=1; //cell按照正常输入，需要-1
            Row sheetRow=currentSheet.getRow(x);
            System.out.println(sheetRow.getCell(y).getStringCellValue());
        }else {
            System.out.println("输入有误，请输入大于0的正整数");
        }
    }

    //遍历Excel文件中的所有值
    public void getExcel(String path,int sheetNum) throws Exception {
        Sheet currentSheet=loadExcel(path,sheetNum);
        int rows=currentSheet.getPhysicalNumberOfRows(); //获取行数
        int columns=currentSheet.getRow(0).getPhysicalNumberOfCells(); //获取列数
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                String cell=currentSheet.getRow(i).getCell(j).toString();
                System.out.println(cell);

            }
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
