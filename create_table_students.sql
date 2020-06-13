create table dev3_db.public.students
(
    id bigint not null
        constraint students_pk
            primary key,
	name text not null,
    passport integer not null
);

alter table dev3_db.public.students owner to postgres;

create unique index students_id_uindex
    on students (id);

CREATE SEQUENCE students_id_seq MINVALUE 1 NO MAXVALUE CYCLE;
ALTER TABLE students
    ALTER COLUMN id
        SET DEFAULT nextval('students_id_seq');
ALTER SEQUENCE students_id_seq OWNED BY students.id;