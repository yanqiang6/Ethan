package utils;

import api.model.ConfigModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

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

}
