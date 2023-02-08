package edu.miu.cs.cs425.mystudentmgmtappwebsecurity.service;

import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.model.Classroom;

public interface ClassroomService {

    public Classroom addNewClassroom(Classroom newClassroom);

    Classroom saveClassroom(Classroom newClassroom);


}
