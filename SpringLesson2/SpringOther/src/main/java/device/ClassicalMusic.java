package device;


import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class ClassicalMusic implements Music{
    private List<String> songsList = new ArrayList<String>();

    {
        songsList.add("classicSong1");
        songsList.add("classicSong2");
    }
    public List<String> getSongs() {
        return songsList;
    }
}
