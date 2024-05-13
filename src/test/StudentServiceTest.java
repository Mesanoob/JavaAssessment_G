import com.generation.model.Student;
import com.generation.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private StudentService studentService;
    private Student student;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
        // Convert LocalDate to java.util.Date
        LocalDate localDate = LocalDate.of(1998, 4, 25);
        Date date = Date.valueOf(localDate);  // java.sql.Date is compatible with java.util.Date
        student = new Student("S001", "John Doe", "johndoe@example.com", date);
    }

    @Test
    void subscribeStudent() {
        studentService.subscribeStudent(student);
        assertTrue(studentService.isSubscribed("S001"), "Student should be subscribed after calling subscribeStudent.");
    }

    @Test
    void findStudent() {
        assertNull(studentService.findStudent("S001"), "Should return null for an unsubscribed student ID.");
        studentService.subscribeStudent(student);
        Student foundStudent = studentService.findStudent("S001");
        assertNotNull(foundStudent, "Should find a student for a subscribed student ID.");
        assertEquals("John Doe", foundStudent.getName(), "The retrieved student's name should match.");
    }
}
