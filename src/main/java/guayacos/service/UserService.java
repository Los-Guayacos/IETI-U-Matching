package guayacos.service;

import guayacos.config.exception.UserNotFoundException;
import guayacos.controller.dto.UserDto;
import guayacos.repository.document.User;

import java.util.List;

public interface UserService {

    User create(UserDto userDto);
    User findByUsername( String userName );
    List<User> getAll();
    User findById( String id ) throws UserNotFoundException;
    User findByEmail( String email ) throws UserNotFoundException;
    User update(UserDto userDto, String id);
    boolean deleteById( String id );
}
