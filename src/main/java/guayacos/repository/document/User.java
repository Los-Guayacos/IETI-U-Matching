package guayacos.repository.document;

import guayacos.controller.dto.UserDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    @Id
    private String id;

    private String name;
    private String lastName;
    private String userName;
    private String passwordHash;
    @Indexed( unique = true )
    private String email;
    private String address;
    private String age;
    private String university;
    private String carreer;
    private String semester;

    public User() {
    }
    public User(UserDto userDto){
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.userName= userDto.getUserName();
        this.passwordHash= userDto.getPassword();
        this.email=userDto.getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCarreer() {
        return carreer;
    }

    public void setCarreer(String carreer) {
        this.carreer = carreer;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void update(UserDto userDto) {
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.userName = userDto.getUserName();
        this.email = userDto.getEmail();
        this.passwordHash=userDto.getPassword();

    }
}
