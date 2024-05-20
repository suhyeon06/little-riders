package kr.co.littleriders.backend.domain.teacher;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;

import java.util.List;

public interface TeacherService {

	Teacher findById(long id);

	boolean existsById(long id);

	boolean notExistsById(long id);

	long save(Teacher teacher);

    List<Teacher> findByAcademy(Academy academy);

    Teacher findByCardNumber(String cardNumber);
}
