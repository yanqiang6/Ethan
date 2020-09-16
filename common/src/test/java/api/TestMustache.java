package api;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;

/**
 * @version 1.0
 * @Author Ethan
 * @Date 2020/9/16 22:28 Create
 */
public class TestMustache {
    @Test
    //mustache模板技术，替换common.mian.resources.mustache下的user.json文件
    public void template() throws IOException {
        HashMap<String, Object> scopes = new HashMap<String, Object>();
        scopes.put("name", "Mustache");

        Writer writer = new OutputStreamWriter(System.out);
        MustacheFactory mf = new DefaultMustacheFactory();
        //获取user.json的绝对路径
        String path=this.getClass().getResource("/mustache/user.json").getPath();
        Mustache mustache=mf.compile(path);

        mustache.execute(writer, scopes);
        writer.flush();
    }
}
