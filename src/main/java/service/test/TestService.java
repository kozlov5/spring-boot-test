package service.test;

import entity.Car;
import entity.Owner;

import javax.validation.constraints.NotNull;

/**
 * Created by Vladimir on 19.08.2017.
 */
public interface TestService {

	String getName();

	String testServiceMethod();

	Car findById(final @NotNull Long id);
}
