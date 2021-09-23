package guayacos.service;

import guayacos.controller.dto.UserDto;
import guayacos.repository.document.User;

public interface UserService {

    User create(UserDto userDto);

}
