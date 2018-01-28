package portfolio.garlicsauce.learntogether.student;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> student = studentRepository.findByLogin(username);

        if (student.isPresent()) {
            return new User(student.get().getLogin(), student.get().getPassword(), Collections.emptyList());
        }

        throw new UsernameNotFoundException(username);
    }

    public Student createStudent(StudentData studentData) {
        return studentRepository.save(studentMapper.map(studentData));
    }

    public Student findStudent(long id) {
        return studentRepository.findOne(id);
    }
}
