package by.ladyka.club;

import by.ladyka.club.config.Config;
import by.ladyka.club.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClubApplication {
	public static final String PACKAGES_TO_SCAN = ClubApplication.class.getPackage().getName();

	public static void main(String[] args) {
		SpringApplication.run(new Class[]{ClubApplication.class, DataSourceConfig.class, Config.class}, args);
	}
}
