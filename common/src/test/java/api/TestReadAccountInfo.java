package api;


import org.junit.jupiter.api.Test;
import utils.BaseUtils;


import java.io.IOException;

/**
 * @version 1.0
 * @Author Ethan
 * @Date 2020/8/23 19:26 Create
 */
//测试[common] utils.BaseUtils中的loadConfigFile方法
public class TestReadAccountInfo extends BaseUtils{
    @Test
    public void tesReadAccountInfo() throws IOException {
        String path=getPath("\\src\\main\\resources\\config\\DemoConfig.yaml");
        readAccountInfo(path,"test");

    }
}
