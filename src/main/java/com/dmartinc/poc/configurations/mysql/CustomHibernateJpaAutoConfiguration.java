package com.dmartinc.poc.configurations.mysql;

import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.dmartinc.poc.model.PocModelConstants.PROFILE_MYSQL;

@Profile(PROFILE_MYSQL)
@Configuration
public class CustomHibernateJpaAutoConfiguration extends HibernateJpaAutoConfiguration {
}
