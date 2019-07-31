package client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("client");
        context.refresh();
        CameraImpl camera = context.getBean("camera", CameraImpl.class);
        camera.breaking();
        camera.doPhotograph();
        camera = context.getBean("camera", CameraImpl.class);
        camera.doPhotograph();
    }
}
