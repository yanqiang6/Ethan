package baseApi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * @version 1.0
 * @Author Ethan
 * @Date 2020/9/3 23:24 Create
 */
public class BaseApi {
    //post请求
    public Response post(String url, HashMap map){
        Response res=
                given()
                        .formParams(map)
                        .when().post(url)
                        .then()
                        .log().all()
                        .extract().response();
        return  res;
    }
    //get请求,不传参数
    public Response get(String url){
        Response res=
                given()
                        .when().get(url)
                        .then()
                        .log().all()
                        .extract().response();
        return  res;
    }
    //get请求,需要传参
    public Response get(String url, HashMap map){
        Response res=
                given()
                        .queryParams(map)
                        .when().get(url)
                        .then()
                        .log().all()
                        .extract().response();
        return  res;
    }
    //response转json
    public JSONObject resToJson(Response res){
        return JSON.parseObject(res.getBody().asString());
    }

}
