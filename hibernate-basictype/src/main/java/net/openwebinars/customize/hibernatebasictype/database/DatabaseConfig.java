package net.openwebinars.customize.hibernatebasictype.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    public DatabaseConfig(@Value("${spring.jpa.hibernate.ddl-auto}") String ddlAuto,
                          @Value("${spring.datasource.properties.hibernate.dialect}") String dialect,
                          @Value("${spring.jpa.show-sql}") String showSql ) {
        this.ddlAuto = ddlAuto;
        this.hibernateDialect = dialect;
        this.showSql = showSql;
    }


    private String ddlAuto;
    private String hibernateDialect;
    private String showSql;

    @Autowired
    private DataSource dataSource;

    @Bean
    public SessionFactory sessionFactory() {
        final LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);

        builder.scanPackages("net.openwebinars.customize.hibernatebasictype.model");

        builder.setProperty("hibernate.dialect", hibernateDialect);
        builder.setProperty(AvailableSettings.HBM2DDL_AUTO, ddlAuto);
        builder.setProperty(AvailableSettings.SHOW_SQL, showSql);
        builder.setProperty(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "true");


        builder.registerTypeContributor( ((typeContributions, serviceRegistry) -> {
            typeContributions.contributeType(BigIntegerStringType.INSTANCE);

        }) );

        return builder.buildSessionFactory();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }




}
