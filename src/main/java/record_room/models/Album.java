package record_room.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Album {
    List<Artist> artists;
    List<Image> images;
    String href;
    String name;
    String type;

    @JsonProperty("external_urls")
    ExternalUrls externalUrls;

    @JsonProperty("release_date")
    String releaseDate;

    @JsonProperty("total_tracks")
    int totalTracks;
}
