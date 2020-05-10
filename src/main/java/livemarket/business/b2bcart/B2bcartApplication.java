package livemarket.business.b2bcart;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class B2bcartApplication {

    public static void main(String[] args) {
        SpringApplication.run(B2bcartApplication.class, args);
    }

}
