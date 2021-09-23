package guayacos.service;


import guayacos.config.exception.UserNotFoundException;
import guayacos.dto.UserDto;
import guayacos.entities.User;

import java.util.List;

public interface UserService
{
    User create(UserDto userDto );

    User findById( String id )
            throws UserNotFoundException;

    User findByEmail( String email )
            throws UserNotFoundException;

    List<User> all();

    boolean deleteById( String id );

    User update( UserDto userDto, String id );
}

