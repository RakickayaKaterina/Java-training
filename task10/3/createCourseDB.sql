create database CoursePlanner DEFAULT CHARACTER SET utf8 ;
use CoursePlanner;
CREATE TABLE `Lector` (
  `idLector` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `work_experience` SMALLINT default 0,
  `science_degree` VARCHAR(55) NULL,
  PRIMARY KEY (`idLector`))
  ;
CREATE TABLE `Student` (
  `idStudent` BIGINT NOT NULL AUTO_INCREMENT,
  `nameStudent` VARCHAR(75) NOT NULL,
  `age` SMALLINT,
  PRIMARY KEY (`idStudent`))
  ;
CREATE TABLE `Course` (
  `idCourse` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(250),
  `start_date` DATE,
  `end_date` DATE,
  `Lector_idLector` BIGINT,
  PRIMARY KEY (`idCourse`),
  CONSTRAINT `fk_Course_Lector`
    FOREIGN KEY (`Lector_idLector`)
    REFERENCES `Lector` (`idLector`))
;
CREATE TABLE `Course_Student` (
  `Course_idCourse` BIGINT NOT NULL,
  `Student_idStudent` BIGINT NOT NULL,
  PRIMARY KEY (`Course_idCourse`, `Student_idStudent`),
  CONSTRAINT `fk_Course_Student_Course1`
    FOREIGN KEY (`Course_idCourse`)
    REFERENCES `Course` (`idCourse`),
  CONSTRAINT `fk_Course_Student_Student1`
    FOREIGN KEY (`Student_idStudent`)
    REFERENCES `Student` (`idStudent`))
;
CREATE TABLE `Lecture` (
  `idLecture` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(75) NOT NULL,
  `description` VARCHAR(200),
  `Course_idCourse` BIGINT NOT NULL,
  PRIMARY KEY (`idLecture`),
  CONSTRAINT `fk_Lecture_Course1`
    FOREIGN KEY (`Course_idCourse`)
    REFERENCES `Course` (`idCourse`))
;
CREATE TABLE `Lesson` (
  `idLesson` BIGINT NOT NULL AUTO_INCREMENT,
  `date` DATE,
  `count_students` SMALLINT,
  `Lecture_idLecture` BIGINT NOT NULL UNIQUE,
  PRIMARY KEY (`idLesson`),
  CONSTRAINT `fk_Lesson_Lecture1`
    FOREIGN KEY (`Lecture_idLecture`)
    REFERENCES `Lecture` (`idLecture`))
;
  
  