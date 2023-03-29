package si.barbarak.fun7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Fun7Application {

    public static void main(String[] args) {
        SpringApplication.run(Fun7Application.class, args);
    }

}
