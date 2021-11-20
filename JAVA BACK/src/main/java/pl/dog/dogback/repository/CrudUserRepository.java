package pl.dog.dogback.repository;

import org.springframework.data.repository.CrudRepository;
import pl.dog.dogback.entity.UserProfile;

public interface CrudUserRepository extends CrudRepository<UserProfile, Long> {
}
