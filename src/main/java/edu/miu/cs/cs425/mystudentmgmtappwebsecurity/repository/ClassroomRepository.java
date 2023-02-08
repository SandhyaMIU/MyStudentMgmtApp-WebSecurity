package edu.miu.cs.cs425.mystudentmgmtappwebsecurity.repository;

import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.model.Classroom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends CrudRepository<Classroom, Integer> {
}
