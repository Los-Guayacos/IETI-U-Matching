package guayacos.controller;

import java.util.List;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import guayacos.model.entities.User;
import guayacos.model.helpers.Filter;
import guayacos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("createUser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("fetchAll")
    public ResponseEntity<List<User>> fetchAll(@RequestParam(name = "limit", defaultValue = "10") int limit,
                                               @RequestHeader("Authorization") String idToken) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = token.getUid();
            return ResponseEntity.ok().body(userService.fetchAll(limit, uid));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("fetchCustom")
    public ResponseEntity<List<User>> fetchCustom(@RequestBody(required = false) Filter filters,
                                                  @RequestParam(name = "limit", defaultValue = "20") int limit,
                                                  @RequestHeader("Authorization") String idToken) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = token.getUid();
            return ResponseEntity.ok().body(userService.fetchCustom(limit, uid, filters));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("findUserByEmail")
    public ResponseEntity<User> findUserByEmail(@RequestParam("email") String email) {
        try {
            User user = userService.findUserByEmail(email);
            if (user != null)
                return ResponseEntity.ok().body(user);
            else
                return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user, @RequestHeader("Authorization") String idToken) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idToken);
            User userUpdated = null;
            if (user.getUid().equals(token.getUid())) {
                userUpdated = userService.updateUser(user);
            }
            if (userUpdated != null)
                return ResponseEntity.ok().body(userUpdated);
            else
                return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("rateUser")
    public ResponseEntity<?> rateUser(@RequestHeader("Authorization") String idToken,
                                      @RequestParam("userId") String userId, @RequestParam("rate") int rate) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idToken);
            userService.rateUser(token.getUid(), userId, rate);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}