package root;

import configuration.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Vladimir on 19.08.2017.
 */
@SpringBootApplication
@Import({ ControllerConfig.class, ServiceConfig.class, DataConfig.class, UIConfig.class})
public class TestSpringBootGradleMain extends SpringBootServletInitializer {

	public static void main(final String[] args) {
		SpringApplication.run(TestSpringBootGradleMain.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
		return builder.sources(TestSpringBootGradleMain.class);
	}
}
