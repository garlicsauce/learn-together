package portfolio.garlicsauce.learntogether.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    Student createStudent(StudentData studentData) {
        return studentRepository.save(studentMapper.map(studentData));
    }

    Student findStudent(long id) {
        return studentRepository.findOne(id);
    }

    public Optional<Student> findStudent(String username) {
        return studentRepository.findByUsername(username);
    }
}
