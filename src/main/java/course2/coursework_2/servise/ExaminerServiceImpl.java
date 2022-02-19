package course2.coursework_2.servise;


import course2.coursework_2.Question;
import course2.coursework_2.exception.QuestionsMaximumAmountExceededException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private Random random;
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> randomQuestions = new HashSet<>();
        if (amount > questionService.getAll().size() || amount <= 0) {
            throw new QuestionsMaximumAmountExceededException();
        }
        if (amount == questionService.getAll().size()) {
            return randomQuestions = questionService.getAll();
        }
        while (randomQuestions.size() < amount) {
            Question randomQuestion = questionService.getRandomQuestion();
            if (randomQuestions.contains(randomQuestion)) {
                continue;
            }
            randomQuestions.add(randomQuestion);
        }
        return randomQuestions;
    }
}