package record_room.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Albums {
    String href;
    List<Album> items;
}
