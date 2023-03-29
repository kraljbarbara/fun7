package si.barbarak.fun7.service.mapper;

import si.barbarak.fun7.model.User;
import si.barbarak.fun7.repository.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static User mapToDto(UserEntity userEntity) {
        User user = new User();
        user.setUuid(userEntity.getUuid());
        user.setUsername(userEntity.getUsername());
        user.setCreated(userEntity.getCreated());

        return user;
    }

    public static List<User> mapToDtoList(List<UserEntity> userEntityList) {
        List<User> userList = new ArrayList<>();
        userEntityList.forEach(u -> userList.add(mapToDto(u)));

        return userList;
    }
}
