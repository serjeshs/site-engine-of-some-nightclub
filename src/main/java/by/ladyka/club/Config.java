//package by.ladyka.club;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
////@EnableTransactionManagement
////@EnableJpaRepositories
////@EnableJpaAuditing
////@ComponentScan("by.ladyka.club")
////@EntityScan(
////        basePackageClasses = {ClubApplication.class, Jsr310JpaConverters.class}
////)
//public class Config {
//    public static final String DATE_TIME_PATTERN = "yyyy.MM.dd HH:mm:ss";
//
//
//    private final DataSource dataSource;
//
//    @Autowired
//    public Config(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabase(Database.MYSQL);
//        vendorAdapter.setGenerateDdl(true);
//        vendorAdapter.setShowSql(true);
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan(getClass().getPackage().getName());
//        factory.setDataSource(dataSource);
//        factory.afterPropertiesSet();
//        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
//        return factory.getObject();
//    }
////
////
////    @Bean
////    public PlatformTransactionManager transactionManager() {
////        EntityManagerFactory factory = entityManagerFactory();
////        return new JpaTransactionManager(factory);
////    }
////
////
////    @Bean
////    public HibernateExceptionTranslator hibernateExceptionTranslator() {
////        return new HibernateExceptionTranslator();
////    }
////
////    @Bean
////    public AuditorAware<String> createAuditorProvider() {
////        return new SecurityAuditor();
////    }
////
////    @Bean
////    public AuditingEntityListener createAuditingListener() {
////        return new AuditingEntityListener();
////    }
//
////
////    @Bean
////    public Jackson2ObjectMapperBuilder jacksonBuilder() {
////        return new Jackson2ObjectMapperBuilder().indentOutput(true)
////                .featuresToDisable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
////                .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
////                .dateFormat(new SimpleDateFormat(DATE_TIME_PATTERN));
////    }
//
//
////
////
////    @Bean
////    public LocalSessionFactoryBean sessionFactory() {
////        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
////        localSessionFactoryBean.setDataSource(dataSource);
////        localSessionFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
////        Properties hibernateProperties = new Properties();
////        hibernateProperties.setProperty("hibernate.dialect", org.hibernate.dialect.MySQLDialect.class.getName());
////        hibernateProperties.setProperty("hibernate.show_sql", Boolean.TRUE.toString());
////        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
////        return localSessionFactoryBean;
////    }
////
////    public static class SecurityAuditor implements AuditorAware<String> {
////        @Override
////        public Optional<String> getCurrentAuditor() {
////            return Optional.of("none");
////        }
////    }
//}
