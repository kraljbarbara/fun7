package si.barbarak.fun7.service;

import si.barbarak.fun7.model.Action;

public interface IAuditLogService {

    void writeAuditLog(String uuid, Action action);

    Long countChecksByUser(String uuid);
}
