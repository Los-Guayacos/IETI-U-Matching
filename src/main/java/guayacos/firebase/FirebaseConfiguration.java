package guayacos.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import guayacos.chat.components.ChatListener;
import java.io.FileInputStream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class FirebaseConfiguration {

  @PostConstruct
  public void initialize() {
    try {
      System.out.println("Initalizing firebase...");

      FileInputStream serviceAccount = new FileInputStream(
        "serviceAccount.json"
      );

      FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://umatching-d13c4-default-rtdb.firebaseio.com")
        .build();

      if (FirebaseApp.getApps().isEmpty()) {
        FirebaseApp.initializeApp(options);
      }

      ChatListener chatListener = new ChatListener();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
