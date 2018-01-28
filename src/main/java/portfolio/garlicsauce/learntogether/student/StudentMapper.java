package portfolio.garlicsauce.learntogether.student;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final BCryptPasswordEncoder passwordEncoder;

    public Student map(StudentData studentData) {
        if (studentData == null) {
            return null;
        }

        return Student.builder()
                .firstName(studentData.getFirstName())
                .lastName(studentData.getLastName())
                .login(studentData.getLogin())
                .password(passwordEncoder.encode(studentData.getPassword()))
                .build();
    }
}
