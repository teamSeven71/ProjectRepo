package community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
// JPA 감사 기능이 활성화(timestamp 시간 자동 업데이트 하려고)
// test
@EnableJpaAuditing
public class Community {

	public static void main(String[] args) {
		SpringApplication.run(Community.class, args);
	}

}
