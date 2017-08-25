package configuration;

import com.vaadin.spring.annotation.EnableVaadin;
import org.springframework.context.annotation.*;

/**
 * Created by Vladimir on 25.08.2017.
 */
@Configuration
@ComponentScan("ui/**")
@EnableVaadin
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class UIConfig {
}
