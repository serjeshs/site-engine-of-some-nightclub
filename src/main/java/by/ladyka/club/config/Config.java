package by.ladyka.club.config;

import by.ladyka.bepaid.BePaidApi;
import by.ladyka.club.ClubApplication;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

import static by.ladyka.club.ClubApplication.PACKAGES_TO_SCAN;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {
		"by.ladyka.club"
})
@EntityScan(
		basePackageClasses = {ClubApplication.class, Jsr310JpaConverters.class}
)
@AllArgsConstructor
public class Config {

	private final DataSource dataSource;
	private final CustomSettings settings;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.MYSQL);
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(PACKAGES_TO_SCAN);
		factory.setDataSource(dataSource);
		factory.afterPropertiesSet();
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		return factory;
	}

	@Bean
	public BePaidApi bePaidApi() {
		return BePaidApi.getApi(settings.getBePaidPaymentStoreId(), settings.getBePaidPaymentStoreKey(), (requestID, method, url, requestBody, code, resp) -> {
		});
	}


}
