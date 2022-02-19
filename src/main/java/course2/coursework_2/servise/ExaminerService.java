package course2.coursework_2.servise;

import course2.coursework_2.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}