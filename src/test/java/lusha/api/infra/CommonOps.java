package lusha.api.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.Cookie;

import java.util.HashMap;

import static lusha.api.infra.Operations.hashMapToString;

public class CommonOps {

    public static String generateGetViewCartBodyRequest(String token) throws JsonProcessingException {
        var values = new HashMap<String, String>() {{
            put("cookie", token);
            put ("flag", "true");
        }};
        return hashMapToString(values);
    }

    public static String getItemDetailsBodyRequest(String itemId) throws JsonProcessingException {
        HashMap<String, String> values = new HashMap<String, String>() {{
            put("id", itemId);
        }};
        return hashMapToString(values);
    }
}
