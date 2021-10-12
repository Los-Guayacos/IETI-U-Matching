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
    @GetMapping
    public List<User> getAll()
    {
        return userService.getAll();
    }

    @GetMapping( "/{id}" )
    public User findById( @PathVariable String id )
    {
        return userService.findById( id );
    }
    @GetMapping( "/username/{userName}" )
    public ResponseEntity<User> findByUserName( @PathVariable String userName )
    {
        return ResponseEntity.ok( userService.findByUserName( userName ) );
    }
    @GetMapping("/email/{id}")
    public User findByEmail(@PathVariable String id ){return userService.findByEmail(id);}

    @GetMapping("/gender/hombre")

    public List<User> findByGenderMan(){return userService.findByGenderMan();}

    @GetMapping("/gender/mujer")

    public List<User> findByGenderWoman(){return userService.findByGenderWoman();}

    @PutMapping( "/{id}" )
    public ResponseEntity<User> update( @RequestBody UserDto userDto, @PathVariable String id )
    {
        return ResponseEntity.ok( userService.update( userDto, id ) );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id )
    {
        return ResponseEntity.ok( userService.deleteById( id ) );
    }


}
