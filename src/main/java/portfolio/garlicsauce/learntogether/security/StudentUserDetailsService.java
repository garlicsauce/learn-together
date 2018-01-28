package portfolio.garlicsauce.learntogether.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import portfolio.garlicsauce.learntogether.student.Student;
import portfolio.garlicsauce.learntogether.student.StudentRepository;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> student = studentRepository.findByLogin(username);

        if (student.isPresent()) {
            return new User(student.get().getLogin(), student.get().getPassword(), Collections.emptyList());
        }

        throw new UsernameNotFoundException(username);
    }

}
