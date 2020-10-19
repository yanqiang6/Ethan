package testApi;

import baseApi.BaseApi;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class TestRegister extends BaseApi {
    static String code=null;

    @BeforeAll
    public static void getAuthCode(){
        TestGetAuthCode getAuthCode=new TestGetAuthCode();
        Response response=getAuthCode.testGetAuthCode();
        JSONObject json=JSON.parseObject(response.getBody().asString());
        code=json.get("data").toString();
        System.out.println(code);
    }

    public HashMap User(){
        HashMap map=new HashMap<>();

        map.put("username","123");
        map.put("password","123");
        map.put("telephone","123");
        map.put("authCode",code);
        return map;
    }
    @Test
    public void testRegister(){
        String url ="http://XXX/sso/register";
        post(url,User());
    }
}
