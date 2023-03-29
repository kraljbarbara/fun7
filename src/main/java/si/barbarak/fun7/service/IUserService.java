package si.barbarak.fun7.service;

import si.barbarak.fun7.exception.DoesNotExistException;
import si.barbarak.fun7.model.User;

import java.util.List;

public interface IUserService {

    User getUser(String uuid) throws DoesNotExistException;

    void disableUser(String uuid) throws DoesNotExistException;

    List<User> getAllUsers();

    boolean isActive(String uuid);
}
