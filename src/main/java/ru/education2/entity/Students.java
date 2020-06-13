package ru.education2.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
public class Students {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "students_id_seq")
    @SequenceGenerator(name = "students_id_seq", sequenceName = "students_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "passport", nullable = false)
    private Integer passport;

    public Students(String name, Integer passport) {
        this.name = name;
        this.passport = passport;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPassport() {
        return passport;
    }

    public void setPassport(Integer passport) {
        this.passport = passport;
    }
}
