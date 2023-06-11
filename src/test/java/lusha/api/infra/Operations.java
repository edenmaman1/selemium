package lusha.api.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mortbay.util.ajax.JSON;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class Operations {

    public static Object executeApiCall(HttpRequest httpRequest){
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return JSON.parse(response.body());
    }


    public static String hashMapToString(HashMap<String, String> values) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper
                .writeValueAsString(values);
        System.out.println(str);
        return str;
    }
}
