package service.test.impl;

import dao.CarDAO;
import entity.Car;
import entity.Owner;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.test.TestService;

import javax.validation.constraints.NotNull;

/**
 * Created by Vladimir on 19.08.2017.
 */
@Service("testService1")
public class TestServiceImpl implements TestService {

	@Autowired
	private CarDAO carDAO;

	@Value("${testproperty}")
	private String name;

	public String getName() {
		return name;
	}

	public String testServiceMethod() {
		return "testService1";
	}

	@Override
	public Car findById(final @NotNull Long id) {
		Car car = carDAO.findOne(id);
		Hibernate.initialize(car.getOwner());
		return car;
	}
}
