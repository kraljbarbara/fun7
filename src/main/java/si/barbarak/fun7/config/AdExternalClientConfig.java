package si.barbarak.fun7.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("client.ads")
public class AdExternalClientConfig {

    private String url;
    private String username;
    private String password;
}
