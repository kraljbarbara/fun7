package si.barbarak.fun7.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    private String uuid;
    private String username;
    private Timestamp created;
}
