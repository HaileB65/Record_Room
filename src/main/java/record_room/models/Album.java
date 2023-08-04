package record_room.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Album {
    List<Artist> artists;
    @JsonProperty("external_urls")
    ExternalUrls externalUrls;
    List<Image> images;
    String name;
    @JsonProperty("release_date")
    String releaseDate;
    @JsonProperty("total_tracks")
    int totalTracks;
    String type;
}
