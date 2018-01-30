package by.ladyka.club.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource database() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://127.0.0.1:3306/afisha?characterEncoding=UTF-8")
                .username("afisha")
                .password("afishapass")
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }
}
