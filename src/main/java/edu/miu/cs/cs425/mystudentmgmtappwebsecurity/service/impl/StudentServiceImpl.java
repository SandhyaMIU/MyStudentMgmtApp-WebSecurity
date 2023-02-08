package edu.miu.cs.cs425.mystudentmgmtappwebsecurity.service.impl;

import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.model.Student;
import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.repository.StudentRepository;
import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student newStudent) {
        return studentRepository.save(newStudent);
    }

    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    @Override
    public Student getStudentById(Long studentId) {
//        return studentRepository.findById(studentId);
        Optional<Student> student = studentRepository.findById(studentId);

        if(student.isPresent()){
            return student.get();
        }
        return null;
    }

    @Override
    public Student updateStudentById(Long studentId, Student editedStudent) {

        Student theStudent = studentRepository.findById(studentId).orElse(null);
        Student updatedStudent = null;
        if(theStudent != null){
            theStudent.setMiddleName(editedStudent.getMiddleName());
            theStudent.setFirstName(editedStudent.getFirstName());
            theStudent.setPrimaryTranscript(editedStudent.getPrimaryTranscript());
            theStudent.setClassroom(editedStudent.getClassroom());
            updatedStudent = studentRepository.save(theStudent);
        }
        return updatedStudent;
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }


    @Override
    public void deleteStudentById(Long studentId) {
            studentRepository.deleteById(studentId);
    }

    @Override
    public List<Student> searchStudent(String searchValue) {
        return studentRepository.searchStudentByStudentNumberOrFirstNameOrLastNameOrMiddleName(searchValue, searchValue, searchValue, searchValue);
    }
}
