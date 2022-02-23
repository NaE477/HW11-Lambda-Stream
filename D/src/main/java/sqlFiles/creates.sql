CREATE TABLE IF NOT EXISTS clerks
(
    clerk_id        SERIAL PRIMARY KEY ,
    clerk_firstname VARCHAR(50),
    clerk_lastname  VARCHAR(50),
    clerk_username  VARCHAR(50),
    clerk_password  VARCHAR(50),
    clerk_salary    DOUBLE PRECISION
);
INSERT INTO clerks (clerk_firstname, clerk_lastname, clerk_username, clerk_password, clerk_salary)
VALUES ('admin','admin','admin','admin',2000000);

CREATE TABLE IF NOT EXISTS professors
(
    prof_id        SERIAL PRIMARY KEY ,
    prof_firstname VARCHAR(50),
    prof_lastname  VARCHAR(50),
    prof_username  VARCHAR(50),
    prof_password  VARCHAR(50),
    prof_position  VARCHAR(2)
);

CREATE TABLE IF NOT EXISTS students
(
    student_id        SERIAL PRIMARY KEY ,
    student_firstname VARCHAR(50),
    student_lastname  VARCHAR(50),
    student_username  VARCHAR(50),
    student_password  VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS courses
(
    course_id   SERIAL PRIMARY KEY ,
    course_name VARCHAR(50),
    course_unit INTEGER,
    prof_id     INTEGER,
    term        INTEGER,

    FOREIGN KEY (prof_id) REFERENCES professors(prof_id)
);

CREATE TABLE IF NOT EXISTS course_to_student
(
    course_id   INTEGER,
    student_id  INTEGER,
    grade       DOUBLE PRECISION,

    FOREIGN KEY (course_id) REFERENCES courses(course_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);

CREATE TABLE IF NOT EXISTS term
(
    term    INTEGER
);
INSERT INTO term (term) VALUES (1);