package com.ensa.agile.infrastructure.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class PersistenceBeans {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new ApplicationAuditorAware();
    }
}
