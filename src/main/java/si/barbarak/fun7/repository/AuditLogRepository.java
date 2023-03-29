package si.barbarak.fun7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.barbarak.fun7.model.Action;
import si.barbarak.fun7.repository.entity.AuditLogEntity;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLogEntity, Long> {

    public Long countAllByUserUuidAndAction(String uuid, Action action);
}
