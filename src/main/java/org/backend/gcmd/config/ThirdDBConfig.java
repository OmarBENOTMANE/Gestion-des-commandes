package org.backend.gcmd.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "thirdEntityManagerFactory",
        transactionManagerRef = "thirdTransactionManager",
        basePackages = {"org.backend.gcmd.repository.perstati"}
)
public class ThirdDBConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.third-datasource")
    public DataSourceProperties thirdDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.third-datasource.configuration")
    public DataSource thirdDataSource() {
        return thirdDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "thirdEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean thirdEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(thirdDataSource())
                .packages("org.backend.gcmd.entity.prestati")
                .build();
    }


    @Bean
    public PlatformTransactionManager thirdTransactionManager(
            final @Qualifier("thirdEntityManagerFactory") LocalContainerEntityManagerFactoryBean thirdEntityManagerFactory) {
        return new JpaTransactionManager(thirdEntityManagerFactory.getObject());
    }
}
