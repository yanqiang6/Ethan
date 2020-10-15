package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @version 1.0
 * @Author Ethan
 * @Date 2020/9/1 21:54 Create
 */
public class DBUtil {

    EncryptUtil encrypt=new EncryptUtil();
    BaseUtils baseUtils=new BaseUtils();
    private String url="jdbc:mysql://xxx.xxx.xxx.xxx:3307/honeycomb?useUnicode=true&useDynamicCharsetInfo=false";
    private String confPath=baseUtils.getPath("/src/main/resources/conf/db.conf");
    private String username=null;
    private String password=null;
    private static Connection conn = null;

    public void open() throws SQLException, IOException {
        username=baseUtils.readConfig(confPath,"username");
        password=encrypt.decryptByText(baseUtils.readConfig(confPath,"password"));
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(url,username,password);
            System.out.println("数据库连接成功");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        if(conn !=null){
            try {
                conn.close();
                System.out.println("数据库已关闭");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
