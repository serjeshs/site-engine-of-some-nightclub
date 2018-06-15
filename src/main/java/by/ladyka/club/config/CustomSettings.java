package by.ladyka.club.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@Configuration
public class CustomSettings {
    @Value("${club.files.directory}")
    private String filesDirectory;
}