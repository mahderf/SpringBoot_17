package springboot17_walkthrough.springboot17.repository;

import org.springframework.data.repository.CrudRepository;
import springboot17_walkthrough.springboot17.models.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{

}

