package guayacos.controller;

import guayacos.controller.dto.UserDto;
import guayacos.repository.document.User;
import guayacos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    /*
    @PutMapping( "/{id}" )
    public ResponseEntity<User> update(@RequestBody UserDto userDto, @PathVariable String id )
    {
        return ResponseEntity.ok( userService.update( userDto, id ) );
    }
    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id )
    {
        return ResponseEntity.ok( userService.deleteById( id ) );
    }*/


}
