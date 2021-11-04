package guayacos.controller;

import java.io.IOException;
import java.util.List;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import guayacos.loger.Log;
import guayacos.model.entities.Match;
import guayacos.service.MatchService;
import guayacos.service.UserService;


@RestController
@RequestMapping(value = "/api/v1/matching/")
@CrossOrigin(origins = "*")
public class MatchController {

    @Autowired
    UserService userServices;

    @Autowired
    MatchService matchServices;

    @GetMapping("fetchAll")
    public ResponseEntity<List<Match>> fetchAll(@RequestHeader("Authorization") String idToken) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = token.getUid();
            List<Match> matches = matchServices.fetchAll(uid);
            return ResponseEntity.ok().body(matches);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("likeUser")
    public ResponseEntity<Boolean> likeUser(@RequestParam("userId") String userId,
                                            @RequestHeader("Authorization") String idToken) throws IOException {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idToken);
            boolean matched = userServices.likeUser(token.getUid(), userId);
            return ResponseEntity.ok().body(matched);
        } catch (Exception e) {
            Log.addLine(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("unmatch")
    public ResponseEntity<List<Match>> unmatch(@RequestHeader("Authorization") String idToken,
                                               @RequestParam("unmatchId") String userToUnmatch) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(idToken);
            List<Match> matches = matchServices.unmatch(token.getUid(), userToUnmatch);
            return ResponseEntity.ok().body(matches);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
