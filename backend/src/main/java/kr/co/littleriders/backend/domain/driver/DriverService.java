package kr.co.littleriders.backend.domain.driver;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.driver.entity.Driver;

import java.util.List;

public interface DriverService {

    Driver findById(long id);

    boolean existsById(long id);

    boolean notExistsById(long id);

	long save(Driver driver);

    List<Driver> findByAcademy(Academy academy);

    Driver findByCardNumber(String cardNumber);
}
