CREATE TABLE IF NOT EXISTS clerks
(
    clerk_id        SERIAL PRIMARY KEY ,
    clerk_firstname VARCHAR(50),
    clerk_lastname  VARCHAR(50),
    clerk_username  VARCHAR(50),
    clerk_password  VARCHAR(50),
    clerk_salary    DOUBLE PRECISION
);

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

    FOREIGN KEY (prof_id) REFERENCES professors(prof_id)
);