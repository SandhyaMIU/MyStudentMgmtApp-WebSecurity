package edu.miu.cs.cs425.mystudentmgmtappwebsecurity.repository;

import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> searchStudentByStudentNumberOrFirstNameOrLastNameOrMiddleName(String searchValue1, String searchValue2, String searchValue3, String searchValue4);



}
