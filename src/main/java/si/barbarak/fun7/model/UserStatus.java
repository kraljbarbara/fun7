package si.barbarak.fun7.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserStatus {

    private String multiplayer;
    @JsonProperty("user-support")
    private String userSupport;
    private String ads;
}
