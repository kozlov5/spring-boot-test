package service.test.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.test.TestService;

/**
 * Created by Vladimir on 19.08.2017.
 */
@Service("testService1")
public class TestServiceImpl implements TestService {

	@Value("${testproperty}")
	private String name;

	public String getName() {
		return name;
	}

	public String testServiceMethod() {
		return "testService1";
	}
}
