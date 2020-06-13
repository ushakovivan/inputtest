package ru.education2.service;

import ru.education2.entity.Students;

import java.util.List;

public interface StudentsService {

    public List<Students> getAllStudents();

    public Students getStudentsById(Object id);

    public Students createStudents(Students students);

    public Students updateStudents(Students students);

    public void deleteStudents(Object id);
    


}
