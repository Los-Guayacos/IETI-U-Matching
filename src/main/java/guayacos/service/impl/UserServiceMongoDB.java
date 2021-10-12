package guayacos.service.impl;

import guayacos.config.exception.UserNotFoundException;
import guayacos.controller.dto.UserDto;
import guayacos.repository.UserRepository;
import guayacos.repository.document.User;
import guayacos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<User> getAll()
    {
        return userRepository.findAll();
    }


    @Override
    public User findById( String id )
    {
        Optional<User> optionalUser = userRepository.findById( id );
        if ( optionalUser.isPresent() )
        {
            return optionalUser.get();
        }
        else
        {
            throw new UserNotFoundException();
        }
    }
    @Override
    public User findByUserName(String userName) throws  UserNotFoundException{

        Optional<User> optionalUser = userRepository.findByUserName(userName);
        System.out.println(optionalUser);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        else{
            throw new UserNotFoundException();
        }


    }
    @Override
    public User findByEmail( String email )
            throws UserNotFoundException
    {
        Optional<User> optionalUser = userRepository.findByEmail( email );
        if ( optionalUser.isPresent() )
        {
            return optionalUser.get();
        }
        else
        {
            throw new UserNotFoundException();
        }
    }
    @Override
    public List<User> findByGenderMan()
            throws UserNotFoundException
    {
        List<User> optionalUser = userRepository.findByGender( "Hombre" );
        return optionalUser;
        /*if ( optionalUser.isPresent() )
        {
            return optionalUser.get();
        }
        else
        {
            throw new UserNotFoundException();
        }*/
    }
    public List<User> findByGenderWoman()
            throws UserNotFoundException {
        List<User> optionalUser = userRepository.findByGender("Mujer");
        return optionalUser;
    }

    @Override
    public User update(UserDto userDto, String id) {
        if ( userRepository.findById( id ).isPresent() )
        {
            User user = userRepository.findById( id ).get();
            user.update( userDto );
            userRepository.save( user );
            return user;
        }
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        if ( userRepository.existsById( id ) )
        {
            userRepository.deleteById( id );
            return true;
        }
        return false;
    }


}
