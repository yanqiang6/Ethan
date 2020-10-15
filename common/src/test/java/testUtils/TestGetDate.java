package testUtils;

import org.junit.jupiter.api.Test;
import utils.DateUtils;

/**
 * @author qiang.yan
 * @version 1.0
 * @date 2020/10/15 17:10 Create
 */
public class TestGetDate extends DateUtils {
    @Test
    public void testGetDate(){
        //打印当前日期
        System.out.println(getCurrentDate("yyyy-MM-dd"));
    }
}
