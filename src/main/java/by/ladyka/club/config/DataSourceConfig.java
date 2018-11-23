package by.ladyka.club.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@Getter
@Setter
@EnableConfigurationProperties(LiquibaseProperties.class)
public class DataSourceConfig {

	@Value("${datasource.url}")
	private String dataSourceUrl;
	@Value("${datasource.username}")
	private String dataSourceUsername;
	@Value("${datasource.password}")
	private String dataBasePassword;
	@Value("${datasource.driver-class-name}")
	private String datasourceDriverClassName;

	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.url(dataSourceUrl)
				.username(dataSourceUsername)
				.password(dataBasePassword)
				.driverClassName(datasourceDriverClassName)
				.build();
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public SpringLiquibase liquibase(LiquibaseProperties properties) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog(properties.getChangeLog());
		liquibase.setContexts(properties.getContexts());
		liquibase.setDataSource(dataSource());
		liquibase.setDefaultSchema(properties.getDefaultSchema());
		liquibase.setDropFirst(properties.isDropFirst());
		liquibase.setShouldRun(properties.isEnabled());
		liquibase.setLabels(properties.getLabels());
		liquibase.setChangeLogParameters(properties.getParameters());
		liquibase.setRollbackFile(properties.getRollbackFile());
		return liquibase;
	}

}
