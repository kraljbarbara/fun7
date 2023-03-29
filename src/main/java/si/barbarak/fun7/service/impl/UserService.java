package si.barbarak.fun7.service.impl;

import org.springframework.stereotype.Service;
import si.barbarak.fun7.exception.DoesNotExistException;
import si.barbarak.fun7.model.Action;
import si.barbarak.fun7.model.User;
import si.barbarak.fun7.repository.UserRepository;
import si.barbarak.fun7.repository.entity.UserEntity;
import si.barbarak.fun7.service.IAuditLogService;
import si.barbarak.fun7.service.IUserService;
import si.barbarak.fun7.service.mapper.UserMapper;

import java.util.List;

@Service
public class UserService implements IUserService {

    final
    UserRepository userRepository;

    final
    IAuditLogService auditLogService;

    public UserService(UserRepository userRepository, IAuditLogService auditLogService) {
        this.userRepository = userRepository;
        this.auditLogService = auditLogService;
    }

    public User getUser(String uuid) throws DoesNotExistException {
        UserEntity userEntity = userRepository.findByUuidAndActive(uuid, true)
                .orElseThrow(DoesNotExistException::new);

        auditLogService.writeAuditLog(uuid, Action.GET_USER);

        return UserMapper.mapToDto(userEntity);
    }

    public void disableUser(String uuid) throws DoesNotExistException {
        UserEntity userEntity = userRepository.findByUuidAndActive(uuid, true)
                .orElseThrow(DoesNotExistException::new);

        userEntity.setActive(false);
        userRepository.save(userEntity);

        auditLogService.writeAuditLog(uuid, Action.DELETE_USER);
    }

    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAllByActive(true);

        auditLogService.writeAuditLog(null, Action.LIST_ALL_USERS);

        return UserMapper.mapToDtoList(userEntityList);
    }

    public boolean isActive(String uuid) {
        return userRepository.findByUuidAndActive(uuid, true).isPresent();
    }
}
