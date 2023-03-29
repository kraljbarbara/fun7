package si.barbarak.fun7.service;

import si.barbarak.fun7.exception.ExternalServiceError;

public interface IAdService {

    boolean isEnabled(String cc) throws ExternalServiceError;
}
