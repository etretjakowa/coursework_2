package course2.coursework_2;

import course2.coursework_2.exception.QuestionsMaximumAmountExceededException;
import course2.coursework_2.servise.ExaminerServiceImpl;
import course2.coursework_2.servise.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static course2.coursework_2.JavaExamConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionServiceMock;

    @InjectMocks
    private ExaminerServiceImpl out;

    private final Question question1 = new Question(QUESTION_1, ANSWER_1);
    private final Question question2 = new Question(QUESTION_2, ANSWER_2);
    private final Question question3 = new Question(QUESTION_3, ANSWER_3);
    private final Question question4 = new Question(QUESTION_4, ANSWER_4);
    private final Question question5 = new Question(QUESTION_5, ANSWER_5);
    private final Set<Question> questionsForTest = new HashSet<>();

    public static Stream<Arguments> paramsGetQuestionsTest() {
        return Stream.of(Arguments.of(NEGATIVE_AMOUNT),
                Arguments.of(ZERO_AMOUNT),
                Arguments.of(TOO_BIG_AMOUNT));
    }

    @BeforeEach
    public void setUp() {
        Mockito.when(javaQuestionServiceMock.getAll())
                .thenReturn(Set.of(question1, question2, question3, question4, question5));
    }

    @Test
    void shouldReturnRightNumberOfQuestions() {
        questionsForTest.add(question1);
        questionsForTest.add(question2);
        questionsForTest.add(question3);
        questionsForTest.add(question4);
        questionsForTest.add(question5);
        assertEquals(questionsForTest, out.getQuestions(RIGHT_AMOUNT));
    }

    @ParameterizedTest
    @MethodSource("paramsGetQuestionsTest")
    void testQuestionsMaximumAmountExceededException(int amount) {
        assertThrows(QuestionsMaximumAmountExceededException.class, () -> out.getQuestions(amount));
    }
}