package testApi;

import baseApi.BaseApi;
import com.alibaba.fastjson.JSONObject;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


/**
 * @author qiang.yan
 * @version 1.0
 * @date 2020/10/16 12:56 Create
 */
public class TestGetAuthCode extends BaseApi {

    @Test
    public void testGetAuthCode(){
        HashMap<String,String> telephone=new HashMap<>();
        telephone.put("telephone","123");
        Response response=get("http://XXX.XXX.XXX.XXX:XX92/sso/getAuthCode",telephone);
        Integer status=response.getStatusCode();
        JSONObject json = resToJson(response);
        System.out.println(json);
        assertThat(status,equalTo(200));

    }
}
