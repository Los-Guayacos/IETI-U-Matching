package guayacos.service;

import guayacos.model.entities.Message;
import guayacos.model.entities.User;
import guayacos.model.helpers.Filter;

import java.util.List;

public interface UserService {
    //Crud USER
    void createUser(User user);
    List<User> fetchAll(int limit, String userId);
    List<User> fetchCustom(int limit, String userId, Filter filters);
    User findUserByEmail(String email);
    User findUserById(String uid);
    User updateUser(User user);

    //Rating
    void rateUser(String rater, String userId, int rate);

    //Matching
    boolean likeUser(String liker, String userId);
    boolean verifyMatch(User liker, User userId);

    //Chat
    void sendMessage(Message message);
    List<Message> retrieveMessages(String userId, String receiver, int limit);

}
