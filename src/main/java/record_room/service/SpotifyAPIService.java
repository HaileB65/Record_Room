package record_room.service;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import record_room.models.AccessToken;
import record_room.models.SpotifyAPIResponse;

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

    @Value("${spotifyAPI_client_secret}")
    String spotifyAPI_client_secret;

    @Autowired
    RestTemplate restTemplate;

    public String getAccessToken() throws IOException, InterruptedException {
        String keys = spotifyAPI_client_id + ":" + spotifyAPI_client_secret; //TODO change client keys to variables spotifyAPI_client_id and spotifyAPI_client_secret from above.
        String url = "https://accounts.spotify.com/api/token";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

//        String form = parameters.keySet().stream()
//                .map(key -> key + "=" + URLEncoder.encode(parameters.get(key), StandardCharsets.UTF_8))
//                .collect(Collectors.joining("&"));
        String encoding = Base64.getEncoder().encodeToString(keys.getBytes());

//        HttpClient client = HttpClient.newHttpClient();

//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
//                .headers("Content-Type", "application/x-www-form-urlencoded", "Authorization", "Basic "+encoding)
//                .POST(HttpRequest.BodyPublishers.ofString(form)).build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Authorization", "Basic " + encoding);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

//        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ResponseEntity<AccessToken> response = restTemplate.postForEntity(url, request, AccessToken.class);
        //TODO check for status code and body isn't null
        // use try/catch or if statement to avoid errors
        return response.getBody().getAccessToken();
    }

    //TODO get album search working
    @PostConstruct
    public SpotifyAPIResponse albumSearch() throws IOException, InterruptedException {
        String accessToken = getAccessToken();
        String url ="https://api.spotify.com/v1/search";

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("q", "MilesDavis");
        parameters.add("type", "album");
        parameters.add("market", "ES");
        parameters.add("limit", "10");
        parameters.add("offset", "5");
        parameters.add("access_token", accessToken);

        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParams(parameters)
                .build()
                .toUri();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters);
        ResponseEntity<SpotifyAPIResponse> response = restTemplate.postForEntity(uri, request, SpotifyAPIResponse.class);

        return response.getBody();
    }
}
