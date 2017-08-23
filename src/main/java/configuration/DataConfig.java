package configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Vladimir on 23.08.2017.
 */
@Configuration
@PropertySource("classpath:data.application.properties")
@EntityScan(value = "entity")
@EnableJpaRepositories(value = "dao")
@EnableTransactionManagement
@EnableJpaAuditing
public class DataConfig {
}
