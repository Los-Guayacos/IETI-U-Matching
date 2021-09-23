package guayacos.service.impl;

import guayacos.controller.dto.UserDto;
import guayacos.repository.UserRepository;
import guayacos.repository.document.User;
import guayacos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceMongoDB implements UserService {

    private final UserRepository userRepository;
    public UserServiceMongoDB(@Autowired UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User create(UserDto userDto) {
        return userRepository.save(new User(userDto));
    }

    @Override
    public User findByUsername(String userName) {

        User user = userRepository.findByUserName(userName);
        System.out.println(user);
        return user;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }


}
