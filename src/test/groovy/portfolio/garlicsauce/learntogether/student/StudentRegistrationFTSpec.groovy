package portfolio.garlicsauce.learntogether.student

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles('functional')
class StudentRegistrationFTSpec extends Specification {

    @Autowired
    RestTemplate restTemplate

    @Autowired
    StudentRepository studentRepository

    def objectMapper = new ObjectMapper()

    def setup() {
        studentRepository.deleteAll()
    }

    def cleanup() {
        studentRepository.deleteAll()
    }

    def "should successfully register user on valid POST request"() {
        given: 'valid request'
            def request = StudentData.builder()
                    .firstName('Jon')
                    .lastName('Snow')
                    .username('jon_snow')
                    .password('nightwatch123')
                    .build()

        when: 'rest endpoint is called with Content-type header set to application/json;charset=UTF-8'
            def headers = new HttpHeaders()
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8)
            def httpEntity = new HttpEntity(objectMapper.writeValueAsString(request), headers)
            restTemplate.postForLocation('http://localhost:8080/student', httpEntity)

        then: 'student is persisted in db'
            studentRepository.findByUsername('jon_snow').isPresent()
    }
}
