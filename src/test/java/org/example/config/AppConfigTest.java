package org.example.config;

import liquibase.integration.spring.SpringLiquibase;
import org.example.config.TestAppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(TestAppConfig.class)
public class AppConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testDataSourceBean() {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        assertNotNull(dataSource, "DataSource should not be null");
        assertTrue(dataSource instanceof DataSource, "DataSource should be of correct type");
    }

    @Test
    void testEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory =
                applicationContext.getBean(LocalContainerEntityManagerFactoryBean.class);
        assertNotNull(entityManagerFactory, "EntityManagerFactory should not be null");
    }

    @Test
    void testTransactionManagerBean() {
        JpaTransactionManager transactionManager =
                applicationContext.getBean(JpaTransactionManager.class);
        assertNotNull(transactionManager, "TransactionManager should not be null");
    }

    @Test
    void testLiquibaseBean() {
        SpringLiquibase liquibase = applicationContext.getBean(SpringLiquibase.class);
        assertNotNull(liquibase, "Liquibase should not be null");
    }
}
