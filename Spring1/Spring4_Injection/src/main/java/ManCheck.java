import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ManCheck {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HelloMan man = context.getBean("man", HelloManOnceSay.class);
        System.out.println(man.getName());
    }
}
