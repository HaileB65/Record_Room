package record_room.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Artist {
    @JsonProperty("external_urls")
    ExternalUrls externalUrls;
    String name;
}
