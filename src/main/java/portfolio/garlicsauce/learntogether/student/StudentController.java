package portfolio.garlicsauce.learntogether.student;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/student", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity createStudent(@RequestBody @Valid StudentData request) {
        Long studentId = studentService.createStudent(request).getId();

        UriComponents newResourceUri = UriComponentsBuilder.fromPath(String.format("/student/%s", studentId))
                .build();

        return ResponseEntity.created(newResourceUri.toUri())
                .build();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") long id) {
        return studentService.findStudent(id);
    }
}
