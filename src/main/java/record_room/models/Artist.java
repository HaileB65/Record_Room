package record_room.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Artist {
    ExternalUrls external_urls;
    String name;
}
