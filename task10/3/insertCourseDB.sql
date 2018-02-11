use `courseplanner`
;
START TRANSACTION;
INSERT INTO student(nameStudent, age) values
('Rakickaya Katerina',20),
('Semenov Semen',18),
('Petrov Petr',19),
('Ivanova Svetlana',20),
('Golubev Roman',21),
('Kudrin Dmitry',20),
('Zaharova Elizaveta',17),
('Vasileva Ksenia',21),
('Maksimova Anna',19),
('Aleksandrov Mikhail',18)
;
INSERT INTO lector(name, work_experience,science_degree) values
('Kadan Aleksandr Mihaelovich',36,'PhD'),
('Rudikova Lada Vladimirovna',20,'PhD'),
('Brazuk Andrej Iosifovich',10,'Master')
;
INSERT INTO course(name,description,start_date,end_date,Lector_idLector) values
('Translation Methods','This course is about compilers, interpreters, translators','2017-02-09','2017-06-26',1),
('Basics of information protection','This course is about ways of the information protection','2017-09-01','2017-11-12',1),
('The computer architecture','This course is about computers history','2017-02-09','2017-06-12',3),
('Database','This course is about relational DBs and SQL','2017-09-01','2017-01-12',2)
;
INSERT INTO course_student(Course_idCourse,Student_idStudent) values
(1,1),
(2,1),
(3,1),
(4,1),
(2,2),
(2,3),
(1,4),
(4,4),
(1,5)
;
INSERT INTO lecture(name, Course_idCourse) values
('Regular expressions',1),
('Finite state machines',1),
('Hash function',2),
('The programming language Assembler',3),
('Relational Algebra',4)
;
INSERT INTO lesson(date, count_students,Lecture_idLecture) values
('2017-09-02',20,5),
('2017-02-14',10,4),
('2017-10-20',25,3)
;
COMMIT;


