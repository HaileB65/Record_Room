package record_room.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import record_room.service.SpotifyAPIService;

@RestController
public class SpotifyRESTController {
    @Autowired
    SpotifyAPIService spotifyAPIService;

    @PostMapping("getToken")
    public String getAccessToken(){
        try {
            return "test";
            //spotifyAPIService.getAccessToken()
//            return ResponseEntity.ok();
        }catch (Exception ex) {
            return "Test did not work";
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
