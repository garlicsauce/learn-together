package portfolio.garlicsauce.learntogether.advertisement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import portfolio.garlicsauce.learntogether.student.Student;
import portfolio.garlicsauce.learntogether.student.StudentService;

@Component
@RequiredArgsConstructor
public class AdvertisementMapper {

    private final StudentService studentService;

    Advertisement map(AdvertisementData advertisementData) {
        return Advertisement.builder()
                .university(advertisementData.getUniversity())
                .subject(advertisementData.getSubject())
                .author(retrieveAuthor(advertisementData.getAuthorUsername()))
                .build();
    }

    private Student retrieveAuthor(String authorUsername) {
        return studentService.findStudent(authorUsername)
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "User with username %s doesn't exist", authorUsername)));
    }
}
