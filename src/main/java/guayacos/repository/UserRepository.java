package guayacos.repository;

import guayacos.repository.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email );

    List<User> findByGender(String gender);

}
