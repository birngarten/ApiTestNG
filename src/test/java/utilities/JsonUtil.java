package utilities;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static String convertJavaToJson(Object object) {
        String jsonResult = "";
        try {
            jsonResult = mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            System.out.println("Java objectini Jsona cevirirken exception olustu." + e.getMessage());
        } catch (JsonMappingException e) {
            System.out.println("Java objectini Jsona cevirirken exception olustu." + e.getMessage());
        } catch (IOException e) {
            System.out.println("Java objectini Jsona cevirirken exception olustu." + e.getMessage());
        }
        return jsonResult;
    }

    public static <T> T convertJsonToJava(String json, Class<T> cls) {
        T javaResult = null;
        try {
            javaResult = mapper.readValue(json, cls);
        } catch (JsonParseException e) {
            System.out.println("1-Json'i Java'ya cevirirken Exception olustu" + e.getMessage());
        } catch (JsonMappingException e) {
            System.out.println("2-Json'i Java'ya cevirirken Exception olustu" + e.getMessage());
        } catch (IOException e) {
            System.out.println("3-Json'i Java'ya cevirirken Exception olustu" + e.getMessage());
        }
        return javaResult;

    }

}
