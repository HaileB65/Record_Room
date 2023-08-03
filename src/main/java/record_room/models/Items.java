package record_room.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Items {
    Artist[] artists;
    ExternalUrls external_urls;
    Image[] images;
    String name;
    String release_date;
    int total_tracks;
    String type;
}
