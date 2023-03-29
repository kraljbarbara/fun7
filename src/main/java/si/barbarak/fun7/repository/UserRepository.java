package si.barbarak.fun7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.barbarak.fun7.repository.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUuidAndActive(String uuid, boolean active);

    List<UserEntity> findAllByActive(boolean active);
}
