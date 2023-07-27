package record_room.service;

import jakarta.annotation.PostConstruct;
import models.SpotifyAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SpotifyAPIService {
    @Value("${spotifyAPI_client_id}")
    String spotifyAPI_client_id;

    @Value("${spotifyAPI_client_id}")
    String spotifyAPI_client_secret;

    @Autowired
    RestTemplate restTemplate;

    @PostConstruct
    public SpotifyAPIResponse getAccessToken(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        String body = "grant_type=client_credentials&client_id=" + spotifyAPI_client_id + "&client_secret=" + spotifyAPI_client_secret;

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        String url = "https://accounts.spotify.com/api/token";

        ResponseEntity<SpotifyAPIResponse> response = restTemplate.postForEntity(url, request, SpotifyAPIResponse.class);
        System.out.println("test");
        return response.getBody();
    }
}
