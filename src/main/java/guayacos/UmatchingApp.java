package guayacos;

import java.io.IOException;
import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class UmatchingApp {

  public static void main(String[] args) throws IOException {
    SpringApplication app = new SpringApplication(UmatchingApp.class);
    app.setDefaultProperties(
      Collections.singletonMap("server.port", getPort())
    );
    app.run(args);
  }

  static int getPort() {
    if (System.getenv("PORT") != null) {
      return Integer.parseInt(System.getenv("PORT"));
    }
    return 8080;
  }
}
