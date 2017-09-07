package springboot17_walkthrough.springboot17.repository;

import org.springframework.data.repository.CrudRepository;
import springboot17_walkthrough.springboot17.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
    User findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);
Iterable<User>findAllByUsername(String username);
Iterable<User>findAllByEmail(String email);
}
