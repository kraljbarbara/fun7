package si.barbarak.fun7.service.impl;

import org.springframework.stereotype.Service;
import si.barbarak.fun7.model.Action;
import si.barbarak.fun7.repository.AuditLogRepository;
import si.barbarak.fun7.repository.entity.AuditLogEntity;
import si.barbarak.fun7.service.IAuditLogService;

@Service
public class AuditLogService implements IAuditLogService {

    final
    AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void writeAuditLog(String uuid, Action action) {
        //auditLogRepository.
        AuditLogEntity auditLogEntity = new AuditLogEntity();
        auditLogEntity.setUserUuid(uuid);
        auditLogEntity.setAction(action);

        auditLogRepository.save(auditLogEntity);
    }

    public Long countChecksByUser(String uuid) {
        return auditLogRepository.countAllByUserUuidAndAction(uuid, Action.CHECK);
    }
}
