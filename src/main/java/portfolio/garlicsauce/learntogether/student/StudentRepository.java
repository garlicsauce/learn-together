package portfolio.garlicsauce.learntogether.student;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Optional<Student> findByLogin(String login);
}
