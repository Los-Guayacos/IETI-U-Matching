package guayacos.controller;

import guayacos.controller.dto.UserDto;
import guayacos.repository.document.User;
import guayacos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/v1/user" )
public class UserController {

    private final UserService userService;
    public UserController( @Autowired UserService userService )
    {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDto userDto )
    {
        return ResponseEntity.ok( userService.create( userDto ) );
    }
    @GetMapping( "/{userName}" )
    public ResponseEntity<User> findByUsername( @PathVariable String userName )
    {
        return ResponseEntity.ok( userService.findByUsername( userName ) );
    }
    @GetMapping
    public List<User> all()
    {
        return userService.getAll();
    }

    @GetMapping( "/{id}" )
    public User findById( @PathVariable String id )
    {
        return userService.findById( id );
    }


}
