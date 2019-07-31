package device;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class RockMusic implements Music {
    private List<String> songsList = new ArrayList<String>();

    {
        songsList.add("rockSong1");
        songsList.add("rockSong2");
    }

    public List<String> getSongs() {
        return songsList;
    }
}
