package record_room.service;

import jakarta.annotation.PostConstruct;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import record_room.models.AccessToken;
import record_room.models.SpotifyAlbumSearchResponse;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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

        String encoding = Base64.getEncoder().encodeToString(keys.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Authorization", "Basic " + encoding);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<AccessToken> response = restTemplate.postForEntity(url, request, AccessToken.class);
        //TODO check for status code and body isn't null
        // use try/catch or if statement to avoid errors
        return response.getBody().getAccessToken();
    }

    //TODO get album search working
    @PostConstruct
    public SpotifyAlbumSearchResponse albumSearch() throws IOException, InterruptedException {
        String q = "MilesDavis";
        String type = "album";
        String market = "ES";
        String limit = "10";
        String offset = "5";
        String accessToken = getAccessToken();

        String url ="https://api.spotify.com/v1/search";

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("q", "{q}")
                .queryParam("type", "{type}")
                .queryParam("market", "{market}")
                .queryParam("limit", "{limit}")
                .queryParam("offset", "{offset}")
                .encode()
                .toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("q", q);
        params.put("type", type);
        params.put("market", market);
        params.put("limit", limit);
        params.put("offset", offset);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<SpotifyAlbumSearchResponse> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                request,
                SpotifyAlbumSearchResponse.class,
                params);

        return response.getBody();
    }
}
