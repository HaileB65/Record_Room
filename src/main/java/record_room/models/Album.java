package record_room.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Album {
    List<Artist> artists;
    ExternalUrls external_urls;
    List<Image> images;
    String name;
    String release_date;
    int total_tracks;
    String type;
}
