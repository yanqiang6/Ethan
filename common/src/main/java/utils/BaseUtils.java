package utils;

import api.model.ConfigModel;

import java.io.IOException;
import java.util.HashMap;

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

    //加载配置文件
    public HashMap<String,String> loadConfigFile(String path, String currentEnv) throws IOException {
        ConfigModel configModel=new ConfigModel();
        //根据当前环境，获取环境对应的账号和密码
        HashMap<String,String> accountInfo= configModel.load(path).env.get(currentEnv);
//        for (Map.Entry<String,String > info:accountInfo.entrySet()){
//            System.out.println(info.getKey()+":"+info.getValue());
//        }
        return accountInfo;
    }
}
