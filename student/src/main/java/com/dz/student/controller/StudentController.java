package com.dz.student.controller;

import com.dz.student.dto.ResponseDTO;
import com.dz.student.model.Student;
import com.dz.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public ResponseEntity<Student> getStudentInfo(@RequestParam int userId) {
        if (Objects.nonNull(studentService.getStudentById(userId))){
            return new ResponseEntity<>(studentService.getStudentById(userId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addStudent")
    @ResponseBody
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/getStudents")
    public ResponseDTO<Student> getStudents() {
        return studentService.getStudents();
    }
}
