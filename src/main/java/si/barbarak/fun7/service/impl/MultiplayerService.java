package si.barbarak.fun7.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import si.barbarak.fun7.service.IAuditLogService;
import si.barbarak.fun7.service.IMultiplayerService;

@Service
public class MultiplayerService implements IMultiplayerService {

    final
    IAuditLogService auditLogService;

    public MultiplayerService(IAuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @Cacheable(value = "multiplayer", unless = "#result == false")
    public boolean isEnabled(String uuid, String cc) {
        if (!"us".equalsIgnoreCase(cc)) {
            return false;
        }

        return auditLogService.countChecksByUser(uuid) > 5;
    }
}
