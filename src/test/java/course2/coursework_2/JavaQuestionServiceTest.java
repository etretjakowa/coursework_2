package course2.coursework_2;

import course2.coursework_2.exception.QuestionEmptyCollectionException;
import course2.coursework_2.exception.QuestionExistsException;
import course2.coursework_2.exception.QuestionNotFoundException;
import course2.coursework_2.servise.JavaQuestionService;
import org.junit.jupiter.api.Test;


import java.util.HashSet;
import java.util.Set;

import static course2.coursework_2.JavaExamConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {


    private final JavaQuestionService out = new JavaQuestionService();
    private Question questionForTest;
    private Question questionForTest5;
    private final Set<Question> questionsForTest = new HashSet<>();


    @Test
    void testAddQuestionAnswer() {
        assertEquals(questionForTest, out.add(QUESTION_1, ANSWER_1));
    }

    @Test
    void testThrowQuestionExistsException() {
        assertThrows(QuestionExistsException.class,
                () -> out.add(QUESTION_5, ANSWER_5));
    }


    @Test
    void testNotThrowExceptionRemove() {
        assertDoesNotThrow(() -> out.remove(questionForTest5));
    }


    @Test
    void testQuestionNotFoundExceptionRemove() {
        assertThrows(QuestionNotFoundException.class,
                () -> out.remove(questionForTest));
    }

    @Test
    void testGetAll() {
        assertEquals(questionsForTest, out.getAll());
    }

    @Test
    void testAdd() {
        Question actualQuestion = new Question(QUESTION_1, ANSWER_1);
        out.add(questionForTest);
        assertEquals(questionForTest, out.find(actualQuestion));
    }

    @Test
    void testFind() {
        assertEquals(questionForTest5, out.find(questionForTest5));
    }

    @Test
    void testQuestionNotFoundException() {
        assertThrows(QuestionNotFoundException.class,
                () -> out.find(questionForTest));
    }


    @Test
    void testQuestionEmptyCollectionException() {
        out.remove(questionForTest5);
        assertThrows(QuestionEmptyCollectionException.class,
                out::getAll);
    }

    @Test
    void getRandomQuestion() {
        assertEquals(questionForTest5, out.getRandomQuestion());
    }
}