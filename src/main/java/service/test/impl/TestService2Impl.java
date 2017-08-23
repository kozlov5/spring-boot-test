package service.test.impl;

import entity.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.test.TestService;

import javax.validation.constraints.NotNull;

/**
 * Created by Vladimir on 19.08.2017.
 */
@Service("testService2")
public class TestService2Impl implements TestService {

	@Value("${testproperty}")
	private String name;

	public String getName() {
		return name;
	}

	public String testServiceMethod() {
		return "testService2";
	}

	@Override
	public Car findById(final @NotNull Long id) {
		return null;
	}
}
