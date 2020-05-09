package com.dz.student.service.impl;

import com.dz.student.dto.ResponseDTO;
import com.dz.student.model.Student;
import com.dz.student.repository.StudentDao;
import com.dz.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    @Override
    public Student getStudentById(int userId) {
        try {
            return studentDao.getStudentById(userId);
        } catch (Exception e) {
            throw new RuntimeException("User does not exits");
        }
    }

    @Override
    public Student addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public ResponseDTO<Student> getStudents() {
        ResponseDTO<Student> responseDTO = new ResponseDTO();
        responseDTO.setCount(studentDao.getStudents().size());
        responseDTO.setData(studentDao.getStudents());
        return responseDTO;
    }
}
