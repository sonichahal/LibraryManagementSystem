package com.dz.student.service;

import com.dz.student.dto.ResponseDTO;
import com.dz.student.model.Student;

public interface StudentService {

    Student getStudentById(int userId);

    Student addStudent(Student student);

    ResponseDTO<Student> getStudents() throws InterruptedException;
}
