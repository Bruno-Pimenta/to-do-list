package com.bruno_pimenta_dev.to_do_list;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Slf4j
@Configuration
public class FlywayConfig {

    @Value("${spring.flyway.env}")
    private String env;

    @Value("${spring.flyway.locations.common}")
    private String common;

    @Value("${spring.flyway.locations.environment}")
    private String environmentLocation;

    @Value("${spring.flyway.table}")
    private String table;

    @Bean
    public Flyway flyway(@Value("${spring.datasource.url}") String url,
                         @Value("${spring.datasource.username}") String user,
                         @Value("${spring.datasource.password}") String password,
                         @Value("${spring.flyway.enabled}") boolean flywayEnabled) {


        if (!flywayEnabled) {
            return null;
        }

        Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .baselineOnMigrate(true)
                .validateMigrationNaming(true)
                .validateOnMigrate(true)
                .baselineVersion("0")
                .table(table)
                .locations(common, environmentLocation + env)
                .load();

        try {
            flyway.migrate();
        } catch (Exception e) {
            if(e.getMessage().contains("Migrations have failed validation")) {
                log.info("Failure migration detected ++++++++++++++++{}",e.getMessage());
                flyway.repair();
                flyway.migrate();
            }
        }
        return flyway;
    }
}