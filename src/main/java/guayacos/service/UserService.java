package guayacos.service;

import guayacos.config.exception.UserNotFoundException;
import guayacos.controller.dto.UserDto;
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
/*

    User findByUserName( String userName ) throws UserNotFoundException;
    List<User> getAll();
    User findById( String id ) throws UserNotFoundException;
    User findByEmail( String email ) throws UserNotFoundException;
    User update(UserDto userDto, String id);
    boolean deleteById( String id );

    List<User> findByGenderMan()throws UserNotFoundException;
    List<User> findByGenderWoman()throws UserNotFoundException;
*/
}
