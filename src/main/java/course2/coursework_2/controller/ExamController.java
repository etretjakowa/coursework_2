package course2.coursework_2.controller;

import course2.coursework_2.Question;
import course2.coursework_2.servise.ExaminerServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/get")
public class ExamController {

    private final ExaminerServiceImpl examinerService;

    public ExamController(ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping
    public Collection<Question> getQuestions(int amount) {
        return examinerService.getQuestions(amount);
    }
}