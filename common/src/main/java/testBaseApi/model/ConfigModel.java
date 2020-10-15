package testBaseApi.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @version 1.0
 * @Author Ethan
 * @Date 2020/8/23 17:30 Create
 */
public class ConfigModel {

    public HashMap<String, HashMap<String,String>> env;



    public  ConfigModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path), ConfigModel.class);
    }
}
