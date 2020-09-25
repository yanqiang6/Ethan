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
    public void testLoadExcel() throws Exception {
        Sheet sheet= loadExcel(path,0);
        System.out.println(sheet.getPhysicalNumberOfRows());
        System.out.println(sheet.getRow(0).getPhysicalNumberOfCells());

    }
    @Test
    public void testGetExcelValue() throws Exception{
        getExcelValue(path,0,1,4);
    }


}
