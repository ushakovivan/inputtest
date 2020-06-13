package ru.education2.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.education2.entity.Students;
import ru.education2.repository.StudentsRepository;
import ru.education2.service.StudentsService;

import java.util.List;

@Service
public class DefaultStudentsService implements StudentsService {

    private final StudentsRepository studentsRepository;

    @Autowired
    public DefaultStudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public List<Students> getAllStudents() {
        return studentsRepository.findAll();
    }

    public Students getStudentsById(Object id) {
        Students students;
        if (id == null) {
            throw new RuntimeException("Идентификатор студента не может быть null");
        }

        String parsedIdString;
        try {
            parsedIdString = (String) id;
        } catch (ClassCastException ex) {
            throw new RuntimeException("Не удалось преобразовать идентификатор к нужному типу");
        }
        Long parsedId;
        try{
            parsedId = Long.valueOf(parsedIdString);
        } catch (NumberFormatException ex) {
            throw new RuntimeException("Не удалось преобразовать идентификатор к нужному типу");
        }
        students = studentsRepository.findOne((long) parsedId);

        if (students == null) {
            throw new RuntimeException("Не удалось найти студента с таким id");
        }

        return students;
    }

    public Students createStudents(Students students) {
        if (students == null) {
            throw new RuntimeException("Создаваемый студент не может быть null");
        }
        if (students.getName() == null) {
            throw new RuntimeException("Имя создаваемого студента не может быть null");
        }

        if (students.getPassport() == null) {
            throw new RuntimeException("Паспорт создаваемого студента не может быть null");
        }

        List<Students> studentsWithSamePassport = studentsRepository.findByPassport(students.getPassport());
        if (studentsWithSamePassport.size() > 0) {
            throw new RuntimeException("Паспорт с таким номером уже существует");
        }

        return studentsRepository.save(students);
    }

    public Students updateStudents(Students students) {
        if (students == null) {
            throw new RuntimeException("Обновляемый студент не может быть null");
        }
        if (students.getName() == null) {
            throw new RuntimeException("Имя обновляемого студента не может быть null");
        }

        if (students.getPassport() == null) {
            throw new RuntimeException("Паспорт обновляемого студента не может быть null");
        }

        List<Students> studentsWithSamePassport = studentsRepository.findByPassport(students.getPassport());
        if (studentsWithSamePassport.size() > 1) {
            throw new RuntimeException("Паспортов с таким номером больше одного");
        }

        if (studentsWithSamePassport.size() == 1) {
            if (studentsWithSamePassport.get(0).getId() != students.getId()) {
                throw new RuntimeException("Паспорт с таким номером уже существует");
            }
        }

        Students existedStudents = studentsRepository.findOne(students.getId());
        if (existedStudents == null) {
            throw new RuntimeException("Обновляемый студент не найден");
        }
        return studentsRepository.save(students);
    }

    public void deleteStudents(Object id) {
        Students students = getStudentsById(id);
        studentsRepository.delete(students);
    }
}
