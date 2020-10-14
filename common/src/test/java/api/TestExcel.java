package api;

import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;
import utils.BaseUtils;

/**
 * @author qiang.yan
 * @version 1.0
 * @date 2020/9/24 16:44 Create
 */
public class TestExcel extends BaseUtils {
    String path=getPath("\\src\\main\\resources\\excel\\demo.xlsx");
    @Test
    //test加载Excel，打印某行和列数
    public void testLoadExcel() throws Exception {
        Sheet sheet= loadExcel(path,0);
        System.out.println(sheet.getPhysicalNumberOfRows());
        System.out.println(sheet.getRow(0).getPhysicalNumberOfCells());

    }
    //test获取Excel某行某列的值
    @Test
    public void testGetExcelValue() throws Exception{
        getExcelValue(path,0,1,4);
    }

    //test获取Excel某行某列的值
    @Test
    public void getExcel() throws Exception{
        getExcel(path,0);
    }
}
