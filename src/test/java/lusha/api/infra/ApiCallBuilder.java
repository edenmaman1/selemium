package lusha.api.infra;

import java.net.URI;
import java.net.http.HttpRequest;

public class ApiCallBuilder {

    public static HttpRequest buildViewCartRequestCall(String requestBody){
        return HttpRequest.newBuilder()
                .uri(URI.create("https://api.demoblaze.com/viewcart"))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
    }

    public static HttpRequest buildViewRequestCall(String requestBody){
        return HttpRequest.newBuilder()
                .uri(URI.create("https://api.demoblaze.com/view"))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
    }
}

