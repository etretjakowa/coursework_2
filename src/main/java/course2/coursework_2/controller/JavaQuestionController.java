package course2.coursework_2.controller;

import course2.coursework_2.Question;
import course2.coursework_2.servise.JavaQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {

    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping
    public Set<Question> getAllQuestions() {
        return javaQuestionService.getAll();
    }

    @GetMapping("/add")
    public String addQuestion(@RequestParam String question, @RequestParam String answer) {
        javaQuestionService.add(question, answer);
        return "Вопрос " + question + " с ответом " + answer + " успешно добавлены.";
    }

    @GetMapping("/remove")
    public String removeQuestion(@RequestParam String question, @RequestParam String answer) {
        javaQuestionService.remove(new Question(question, answer));
        return "Вопрос " + question + " с ответом " + answer + " успешно удалены.";
    }

    @GetMapping("/find")
    public String findQuestion(@RequestParam String question, @RequestParam String answer) {
        javaQuestionService.find(new Question(question, answer));
        return "Вопрос " + question + " с ответом " + answer + " найден.";
    }
}
