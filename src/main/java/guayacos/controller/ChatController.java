package guayacos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import guayacos.model.entities.Match;
import guayacos.model.entities.Message;
import guayacos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    UserService userServices;

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody Message message,
                                            @RequestHeader("Authorization") String idToken) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = token.getUid();
            if (uid.equals(message.getAuthorId()))
                userServices.sendMessage(message);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("retrieveMessages")
    public ResponseEntity<List<Message>> retrieveMessages(@RequestHeader("Authorization") String idToken,
                                                          @RequestParam("receiver") String receiver, @RequestParam("limit") int limit) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idToken);
            List<Message> messages = userServices.retrieveMessages(token.getUid(), receiver, limit);
            return ResponseEntity.ok().body(messages);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("retrieveAllMessages")
    public ResponseEntity<Map<String, List<Message>>> retrieveAllMessages(@RequestHeader("Authorization") String idToken,
                                                                          @RequestBody List<Match> matches) {
        Map<String, List<Message>> messages = new HashMap<String, List<Message>>();
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idToken);
            for (int i = 0; i < matches.size(); i++) {
                int limit = i == 0 ? 10 : 1;
                messages.put(matches.get(i).getUserId(),
                        userServices.retrieveMessages(token.getUid(), matches.get(i).getUserId(), limit));
            }
            return ResponseEntity.ok().body(messages);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @MessageMapping("/chat")
    public void receiveMessage(@Payload Message message) {
        template.convertAndSendToUser(message.getReceiver(), "/queue/messages", message);
    }

    @SendTo("/topic/message")
    public Message broadcastMessage(@Payload Message message) {
        return message;
    }

}