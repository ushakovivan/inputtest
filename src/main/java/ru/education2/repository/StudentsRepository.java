package ru.education2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.education2.entity.Students;

import java.util.List;

public interface StudentsRepository extends JpaRepository<Students, Long> {

    List<Students> findByPassport(Integer passport);

}
