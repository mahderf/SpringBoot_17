package springboot17_walkthrough.springboot17.repository;

import org.springframework.data.repository.CrudRepository;
import springboot17_walkthrough.springboot17.models.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{
Iterable<Role>findAllById(Long Long);
Iterable<Role>findRoleByRole(String String);
Role findByRole(String role);

}

