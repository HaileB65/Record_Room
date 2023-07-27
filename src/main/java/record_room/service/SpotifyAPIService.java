package record_room.service;

import org.springframework.stereotype.Service;
import record_room.models.SpotifyAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class SpotifyAPIService {
    @Value("${spotifyAPI_client_id}")
    String spotifyAPI_client_id;

    @Value("${spotifyAPI_client_id}")
    String spotifyAPI_client_secret;

    @Autowired
    RestTemplate restTemplate;

    public SpotifyAPIResponse getAccessToken(){
        String clientCredentials = "2a2a74173789459097512d4d6189a074"+ ":" + "d5559a3ad12b4b66ae1c9001310b1c87";
        String encodedCredentials = Base64.getEncoder().encodeToString(clientCredentials.getBytes(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        headers.set("Authorization", "Basic " + encodedCredentials);

        String body = "grant_type=client_credentials";

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        String url = "https://accounts.spotify.com/api/token";

        ResponseEntity<SpotifyAPIResponse> response = restTemplate.postForEntity(url, request, SpotifyAPIResponse.class);
        System.out.println("test");
        return response.getBody();
    }
}
