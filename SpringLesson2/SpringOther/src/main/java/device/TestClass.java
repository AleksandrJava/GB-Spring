package device;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.scan("device");
        context.refresh();
        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

        musicPlayer.playMusic(MusicEnum.CLASSICAL);
        musicPlayer.playMusic(MusicEnum.ROCK);
    }

}
