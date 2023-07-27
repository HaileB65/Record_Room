package record_room.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class SpotifyAPIService {
    @Value("${spotifyAPI_client_id}")
    String spotifyAPI_client_id;

    @Value("${spotifyAPI_client_id}")
    String spotifyAPI_client_secret;

    @Autowired
    RestTemplate restTemplate;

    public String getAccessToken() throws IOException, InterruptedException {

        String keys = "2a2a74173789459097512d4d6189a074:d5559a3ad12b4b66ae1c9001310b1c87"; //TODO change client keys to variables spotifyAPI_client_id and spotifyAPI_client_secret from above.
        String url = "https://accounts.spotify.com/api/token";

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "client_credentials");

        String form = parameters.keySet().stream()
                .map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
        String encoding = Base64.getEncoder().encodeToString(keys.getBytes());

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .headers("Content-Type", "application/x-www-form-urlencoded", "Authorization", "Basic "+encoding)
                .POST(HttpRequest.BodyPublishers.ofString(form)).build();

        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + response.body().toString());

        return response.body().toString();
    }
}
