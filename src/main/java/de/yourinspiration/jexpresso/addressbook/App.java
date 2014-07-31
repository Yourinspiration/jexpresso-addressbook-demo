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
        final JExpresso app = new JExpresso();

        app.use(new StaticResources("assets", false));

        @SuppressWarnings("resource")
        final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        final AddressRepository repo = ctx.getBean(AddressRepository.class);

        app.get("/address", (req, res) -> {
            res.json(repo.findAll());
        });

        app.get("/address/:id", (req, res) -> {
            res.json(repo.findOne(req.param("id")));
        });

        app.post("/address", (req, res) -> {
            res.json(repo.save(req.json(Address.class)));
        });

        app.delete("/address/:id", (req, res) -> {
            repo.delete(req.param("id"));
            res.send("");
        });

        final int port = System.getenv("PORT") != null ? Integer.valueOf(System.getenv("PORT")) : 8080;

        app.listen(port, () -> {
            Logger.info("Address book demo application is running on port " + port);
        });

    }

    public static void main(String[] args) {
        final App app = new App();
        app.start();
    }
}
