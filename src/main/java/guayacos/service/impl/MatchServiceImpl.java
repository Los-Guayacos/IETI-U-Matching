package guayacos.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import guayacos.model.entities.Match;
import guayacos.model.entities.User;
import guayacos.service.MatchService;
import guayacos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MatchServiceImpl implements MatchService {

    @Autowired
    UserService userServices;

    @Override
    public List<Match> fetchAll(String userId) {
        List<Match> matches = null;
        try {
            Firestore db = FirestoreClient.getFirestore();
            DocumentReference documentReference = db.collection("users").document(userId);
            ApiFuture<DocumentSnapshot> future = documentReference.get();
            DocumentSnapshot documentSnapshot = future.get();
            if (documentSnapshot.exists()) {
                User user = documentSnapshot.toObject(User.class);
                matches = user.getMatches() != null ? user.getMatches() : new ArrayList<Match>();
            } else {
                System.out.println("El usuario no existe");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return matches;
    }

    @Override
    public List<Match> unmatch(String userId, String userToUnmatch) {
        List<Match> updatedMatchesUser = new ArrayList<Match>();
        List<Match> updatedMatchesUnmatch = new ArrayList<Match>();
        try {
            User user = userServices.findUserById(userId);
            User unmatchedUser = userServices.findUserById(userToUnmatch);
            if (user != null && unmatchedUser != null) {
                // Delete from match list
                user.getMatches().stream()
                        .filter(match -> !match.getUserId().equals(userToUnmatch))
                        .map(match -> updatedMatchesUser.add(match));
                unmatchedUser.getMatches().stream()
                        .filter(match -> !match.getUserId().equals(userId))
                        .map(match -> updatedMatchesUnmatch.add(match));
                // Delete from likes list
                List<String> updatedLikesUser = new ArrayList<String>();
                List<String> updatedLikesUnmatch = new ArrayList<String>();
                user.getLikes().stream().filter(like -> !like.equals(userToUnmatch))
                        .map(like -> updatedLikesUser.add(like));
                unmatchedUser.getLikes().stream()
                        .filter(like -> !like.equals(userId))
                        .map(like -> updatedLikesUnmatch.add(like));

                // Delete received likes
                List<String> receivedLikesUser = new ArrayList<>();
                List<String> receivedLikesUnmatch = new ArrayList<>();
                unmatchedUser.getReceivedLikes().stream()
                        .filter(like -> !like.equals(userId))
                        .map(like -> receivedLikesUnmatch.add(like));
                user.getReceivedLikes().stream()
                        .filter(like -> !like.equals(userId))
                        .map(like -> receivedLikesUser.add(like));

                // Update users
                user.setMatches(updatedMatchesUser);
                user.setLikes(updatedLikesUser);
                user.setReceivedLikes(receivedLikesUser);
                unmatchedUser.setMatches(updatedMatchesUnmatch);
                unmatchedUser.setLikes(updatedLikesUnmatch);
                unmatchedUser.setReceivedLikes(receivedLikesUnmatch);
                // Persist
                userServices.updateUser(user);
                userServices.updateUser(unmatchedUser);

            } else {
                System.out.println("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updatedMatchesUser;
    }

}
