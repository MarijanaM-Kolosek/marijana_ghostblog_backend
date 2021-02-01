package ghost.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ghost.blog.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
