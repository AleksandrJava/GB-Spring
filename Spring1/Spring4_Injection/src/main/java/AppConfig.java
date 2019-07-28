import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean(name="man")
    public HelloManOnceSay man() {
        return new HelloManOnceSay() ;
    }
}
