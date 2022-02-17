package course2.coursework_2.servise;


import course2.coursework_2.Question;
import course2.coursework_2.exception.QuestionExistsException;
import course2.coursework_2.exception.QuestionNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private int questionNumber;

    @Override
    public Question add(String question, String answer) {
//        if (!StringUtils.hasLength(question) || !StringUtils.hasLength(answer)) {
//            throw new QuestionOrAnswerIsNullException();
//        }
        Question newQuestion = new Question(question, answer);
        if (questions.contains(newQuestion)) {
            throw new QuestionExistsException();
        }
        questions.add(newQuestion);
        questionNumber++;
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionExistsException();
        }
        questions.add(question);
        questionNumber++;
        return question;
    }

    @Override
    public Question find(Question question) {
        if (questions.contains(question)) {
            return question;
        }
        throw new QuestionNotFoundException();
    }

    @Override
    public Question remove(Question question) {
        if (questions.contains(question)) {
            questions.remove(question);
            questionNumber--;
            return question;
        }
        throw new QuestionNotFoundException();
    }

    @Override
    public Set<Question> getAll() {
        return Set.copyOf(questions);
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int randomNumber = random.nextInt(questionNumber);
        List<Question> questionList = new ArrayList<>(questions);
        return questionList.get(randomNumber);
    }
}