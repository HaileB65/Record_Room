package record_room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import record_room.models.AccessToken;
import record_room.models.Albums;
import record_room.models.SpotifyAlbumSearchResponse;

import java.io.IOException;
import java.util.Base64;

@Service
public class SpotifyAPIService {
    @Value("${spotifyAPI_client_id}")
    String spotifyAPI_client_id;

    @Value("${spotifyAPI_client_secret}")
    String spotifyAPI_client_secret;

    @Autowired
    RestTemplate restTemplate;

    public String getAccessToken() throws IOException, InterruptedException {
        String keys = spotifyAPI_client_id + ":" + spotifyAPI_client_secret;
        String url = "https://accounts.spotify.com/api/token";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        String encoding = Base64.getEncoder().encodeToString(keys.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Authorization", "Basic " + encoding);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<AccessToken> response = restTemplate.postForEntity(url, request, AccessToken.class);
        return response.getBody().getAccessToken();
    }

    public SpotifyAlbumSearchResponse albumSearch() throws IOException, InterruptedException {
        String q = "MilesDavis";
        String type = "album";
        String market = "ES";
        String limit = "10";
        String offset = "5";
        String accessToken = getAccessToken();

        String url = "https://api.spotify.com/v1/search";

        String uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("q", "MilesDavis")
                .queryParam("type", "album")
                .queryParam("market", "ES")
                .queryParam("limit", "10")
                .queryParam("offset", "5")
                .encode()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<SpotifyAlbumSearchResponse> response = restTemplate.exchange(uri, HttpMethod.GET, request, SpotifyAlbumSearchResponse.class);

        return response.getBody();
    }
}
