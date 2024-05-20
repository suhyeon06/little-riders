package kr.co.littleriders.backend.domain.teacher.service;

import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.teacher.TeacherService;
import kr.co.littleriders.backend.domain.teacher.entity.Teacher;
import kr.co.littleriders.backend.domain.teacher.error.code.TeacherErrorCode;
import kr.co.littleriders.backend.domain.teacher.error.exception.TeacherException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public Teacher findById(final long id) {
        return teacherRepository.findById(id).orElseThrow(
                () -> TeacherException.from(TeacherErrorCode.NOT_FOUND)
        );
    }

    @Override
    public boolean existsById(final long id) {
        return teacherRepository.existsById(id);
    }

    @Override
    public boolean notExistsById(final long id) {
        return !teacherRepository.existsById(id);
    }

    @Override
    @Transactional
    public long save(final Teacher teacher) {
        return teacherRepository.save(teacher).getId();
    }

    @Override
    public List<Teacher> findByAcademy(Academy academy) {
        return teacherRepository.findByAcademy(academy);
    }

    @Override
    public Teacher findByCardNumber(String cardNumber) {

        return teacherRepository.findByCardNumber(cardNumber).orElseThrow(
                () -> TeacherException.from(TeacherErrorCode.NOT_FOUND)
        );
    }

}
