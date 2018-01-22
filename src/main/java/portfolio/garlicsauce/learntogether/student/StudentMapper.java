package portfolio.garlicsauce.learntogether.student;

import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student map(StudentData studentData) {
        if (studentData == null) {
            return null;
        }

        return Student.builder()
                .firstName(studentData.getFirstName())
                .lastName(studentData.getLastName())
                .login(studentData.getLogin())
                .password(studentData.getPassword())
                .build();
    }
}
