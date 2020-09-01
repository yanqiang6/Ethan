package utils;

import api.model.ConfigModel;
import com.alibaba.fastjson.JSON;
import io.restassured.response.Response;

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
