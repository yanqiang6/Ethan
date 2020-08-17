package utils;
import org.jasypt.util.text.BasicTextEncryptor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EncryptUtil {
    public static void main(String args[]){

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //读取环境变量中加密的盐
        //String salt=System.getenv("SALT_VALUE");//可以将盐配置到环境变量中
        //System.out.println(salt);
        String salt="vhrkAQ1m";//配置加密所需的salt(盐)
        textEncryptor.setPassword(salt);
        //要加密的数据（用户名或密码）
        String username = textEncryptor.encrypt("admin");
        String password = textEncryptor.encrypt("admin");
        //打印加密后的
        System.out.println("username加密成功:"+username);
        System.out.println("password加密成功:"+password);
        //根据盐值去解密
        try{
            System.out.println(textEncryptor.decrypt("dII6NjRyFpG2hO+7Aco4kURdOFmFHptw"));
        }catch (Exception e){
            System.out.println("待解密样本对应的盐值不正确，解密失败");
        }
    }

    //读取环境变量中设置的盐值,读取配置文件路径解密密码
    public void decrypt(String confPath) throws IOException {
        String salt=System.getenv("SALT_VALUE");
        Properties pro = new Properties();
        pro.load(new FileInputStream(confPath));
        String pwd=pro.getProperty("password");
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(salt);
        try{
            String password=textEncryptor.decrypt(pwd);
            System.out.println(password);
        }catch (Exception e){
            System.out.println("待解密样本对应的盐值不正确，解密失败");
        }

    }
    //读取环境变量中设置的盐值,直接对加密的字符串进行解码
    public String decryptByText(String pwd)  {
        String salt=System.getenv("SALT_VALUE");
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(salt);
        String password=textEncryptor.decrypt(pwd);
        //System.out.println(password);
        return password;
    }


}
