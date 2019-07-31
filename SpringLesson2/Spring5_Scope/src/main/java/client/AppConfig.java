package client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean(name="cameraRoll")
    public CameraRoll cameraRoll() {
        return new ColorCameraRoll();
    }
}

