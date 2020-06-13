package ru.education2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.education2.entity.Students;
import ru.education2.service.StudentsService;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentsController {

    private final StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping
    public List<Students> getAllStudents() {
        return studentsService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Students getStudentsById(@PathVariable Object id) {
        return studentsService.getStudentsById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Students createStudents(@RequestBody Students students) {
        return studentsService.createStudents(students);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Students updateStudents(@RequestBody Students students) {
        return studentsService.updateStudents(students);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudents(@PathVariable Object id) {
        this.studentsService.deleteStudents(id);
    }

}
