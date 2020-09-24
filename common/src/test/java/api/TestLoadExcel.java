package api;

import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;
import utils.BaseUtils;

/**
 * @author qiang.yan
 * @version 1.0
 * @date 2020/9/24 16:44 Create
 */
public class TestLoadExcel extends BaseUtils {
    @Test
    public void testLoadExcel() throws Exception {
        String path=getPath("\\src\\main\\resources\\excel\\demo.xlsx");
        Sheet sheet= loadExcel(path,0);
        System.out.println(sheet.getPhysicalNumberOfRows());
        System.out.println(sheet.getRow(0).getPhysicalNumberOfCells());
    }
}
