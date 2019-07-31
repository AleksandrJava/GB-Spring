package device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MusicPlayer {
    private ClassicalMusic classicalMusic;
    private RockMusic rockMusic;

    @Autowired
    public MusicPlayer(ClassicalMusic classicalMusic, RockMusic rockMusic) {
        this.classicalMusic = classicalMusic;
        this.rockMusic = rockMusic;
    }

    public void playMusic(MusicEnum musicEnum){
        Random rand = new Random();

        int randNumb = rand.nextInt(2);
        if(musicEnum == MusicEnum.CLASSICAL){
            System.out.println(classicalMusic.getSongs().get(randNumb));
        }else{
            System.out.println(rockMusic.getSongs().get(randNumb));
        }

    }

}
