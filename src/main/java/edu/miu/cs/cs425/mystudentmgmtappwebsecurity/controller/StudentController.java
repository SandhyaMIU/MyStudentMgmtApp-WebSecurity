package edu.miu.cs.cs425.mystudentmgmtappwebsecurity.controller;

import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.model.Student;
import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.service.StudentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
//@RequestMapping(value = {"/student", "/secured/student"})
public class StudentController {

    private StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/secured/student/list") //route (url)
    public ModelAndView displayStudents(){
        var modelAndView = new ModelAndView();
        var students = studentService.getAllStudents();
        modelAndView.addObject("students",students);
        modelAndView.setViewName("secured/student/list"); //logical name of the view
        return modelAndView;
    }

    @GetMapping(value ="/secured/student/new")
    public String displayNewStudentForm(Model model){

//        model.addAttribute("student", new Student(null,null,null,null,null,0.0,null,true));
        Student student = new Student();
        model.addAttribute("student",student);
        return ("secured/student/new");
    }

    @PostMapping(value ="/secured/student/new")
    public String registerNewStudent(@Valid @ModelAttribute("student") Student student,
                                     BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("student", student);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/student/new";
        }
        studentService.saveStudent(student);
        return "redirect:/secured/student/list";
    }


    @GetMapping(value = {"/secured/student/edit/{studentId}"})
    public String editPublisher(@PathVariable Long studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        /*if(student != null) {
            model.addAttribute("student", student);
            return "secured/student/edit";
        }*/
        model.addAttribute("student", student);
        return "secured/student/edit";
//        return "redirect:/secured/student/list";
    }


    @PostMapping(value = {"/secured/student/update"})
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
                                  BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("student", student);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/secured/student/edit";
        }
        studentService.updateStudent(student);
        return "redirect:/secured/student/list";
    }


    @GetMapping(value = {"/secured/student/delete/{studentId}"})
    public String deleteStudent(@PathVariable Long studentId, HttpSession httpSession) {
        studentService.deleteStudentById(studentId);
        httpSession.setAttribute("msg","Data is deleted successfully.");
        return "redirect:/secured/student/list";
    }


    @GetMapping(value = {"/secured/student/search"})
    public String search(@RequestParam String searchValue, Model model) {
        List<Student> students = null;
        /*if (searchValue.isBlank()) {
            students = studentService.getAllStudents();
        } else {*/
            students = studentService.searchStudent(searchValue);
        /*}*/

        model.addAttribute("students", students);
        model.addAttribute("searchValue", searchValue);
        return "redirect:/secured/student/list";
    }




}
