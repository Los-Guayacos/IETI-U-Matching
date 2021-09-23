package guayacos.service;

import guayacos.controller.dto.UserDto;
import guayacos.repository.document.User;

import java.util.List;

public interface UserService {

    User create(UserDto userDto);
    User findByUsername( String userName );
    List<User> all();
    User findById( String id ) throws UserNotFoundException;

}
