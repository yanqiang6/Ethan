package ui;

import org.junit.jupiter.api.Test;
import ui.BaseWeb;

import java.io.IOException;

/**
 * @version 1.0
 * @Author Ethan
 * @Date 2020/8/22 22:58 Create
 */
//测试[common] utils.ui.BaseWeb中的openUrl方法
public class TestOpenUrl extends BaseWeb{
    @Test
    public  void testOpenUrl() throws IOException {
        openUrl("https://www.baidu.com",1);
    }
}
