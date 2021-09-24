package guayacos.repository;

import guayacos.repository.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserName(String username);
    Optional<User> findByEmail(String email );
}
