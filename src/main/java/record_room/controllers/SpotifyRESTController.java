package record_room.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import record_room.service.SpotifyAPIService;

@RestController
public class SpotifyRESTController {
    @Autowired
    SpotifyAPIService spotifyAPIService;

    @PostMapping("getToken")
    public ResponseEntity<?> getAccessToken() {
        try {
            return ResponseEntity.ok(spotifyAPIService.getAccessToken());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("albumSearch")
    public ResponseEntity<?> albumSearch() {
        try {
            return ResponseEntity.ok(spotifyAPIService.albumSearch());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
