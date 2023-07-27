package record_room.models;

import java.util.List;

public class Item {
    List<Artist> artists;
    ExternalUrls externalUrls;
    List<Image> images;
    String name;
    String release_date;
    int total_tracks;
    String type;
}
