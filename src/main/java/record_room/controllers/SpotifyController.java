package record_room.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import record_room.models.Album;
import record_room.service.SpotifyAPIService;

import java.io.IOException;
import java.util.List;

@Controller
public class SpotifyController {
    @Autowired
    SpotifyAPIService spotifyAPIService;

    @GetMapping
    public String viewHomePage(Model model) throws IOException, InterruptedException {
        List<Album> itemList = spotifyAPIService.albumSearch().getAlbums().getItems();
        model.addAttribute("itemList", itemList);

        return "home";
    }

}
