package com.dz.student.repository;

import com.dz.student.model.Student;

import java.util.List;

public interface StudentDao {

    Student addStudent(Student student);

    Student getStudentById(int userId);

    List<Student> getStudents();

    Student updateStudent(Student student);

    String deleteStudentById(String userId);

}
