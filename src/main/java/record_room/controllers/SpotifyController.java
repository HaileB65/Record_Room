package record_room.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import record_room.service.SpotifyAPIService;

import java.io.IOException;

@Controller
public class SpotifyController {
    @Autowired
    SpotifyAPIService spotifyAPIService;

    @GetMapping
    public String viewHomePage(Model model) throws IOException, InterruptedException {
        model.addAttribute("albums", spotifyAPIService.albumSearch());

        return "home";
    }

}
