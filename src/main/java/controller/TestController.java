package controller;

import data.dto.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import service.test.TestService;

/**
 * Created by Vladimir on 19.08.2017.
 */
@RestController
public class TestController {

	@Autowired
	@Qualifier("testService1")
	private TestService testService;

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public TestDTO testRequest() {
		TestDTO testDTO = new TestDTO();
		testDTO.setId(1L);
		testDTO.setName(testService.testServiceMethod());

		String b = testService.testServiceMethod();
		String a = "weg";
		return testDTO;
	}
}
