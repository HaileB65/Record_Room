package record_room.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Albums {
    String href;
    Items[] items;
}
