package portfolio.garlicsauce.learntogether.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Student createStudent(StudentData studentData) {
        return studentRepository.save(studentMapper.map(studentData));
    }

    public Student findStudent(long id) {
        return studentRepository.findOne(id);
    }
}
