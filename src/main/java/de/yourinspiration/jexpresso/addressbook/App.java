package de.yourinspiration.jexpresso.addressbook;

import org.pmw.tinylog.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.yourinspiration.jexpresso.JExpresso;
import de.yourinspiration.jexpresso.staticresources.StaticResources;

/**
 * A demo application for JExpresso
 *
 */
public class App {

    private void start() {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class)) {
            final JExpresso jexpresso = ctx.getBean(JExpresso.class);
            jexpresso.use(new StaticResources("assets", false));

            final int port = System.getenv("PORT") != null ? Integer.valueOf(System.getenv("PORT")) : 8080;

            jexpresso.listen(port, () -> {
                Logger.info("Address book demo application is running on port " + port);
            });
        }
    }

    public static void main(String[] args) {
        final App app = new App();
        app.start();
    }
}
