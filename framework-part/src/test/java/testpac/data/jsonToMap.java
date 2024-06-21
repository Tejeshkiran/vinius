package testpac.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class jsonToMap {

    public List<HashMap<String,String>> dataReader() throws IOException{

        //Read jason data to string
        String jsonContent;
        try {
          jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir")+"src//test//java//testpac//data//purchaseOrder.json"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //String to Hashmap- jackson databind(dependency to add in our pom.xml)
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;


    }
}
