package controller;

import dao.OwnerDAO;
import data.dto.TestDTO;
import entity.Car;
import entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import service.test.OwnerService;
import service.test.TestService;

/**
 * Created by Vladimir on 19.08.2017.
 */
@RestController
public class TestController {

	@Autowired
	@Qualifier("testService1")
	private TestService testService;

	@Autowired
	private OwnerDAO ownerDAO;

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public Car testRequest() {

		Car car = testService.findById(17L);
		Owner owner = ownerDAO.findOne(9L);
		return car;
	}
}
