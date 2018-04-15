package portfolio.garlicsauce.learntogether.student

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class StudentControllerSpec extends Specification {

    def objectMapper = new ObjectMapper()

    def serviceMock = Mock(StudentService)
    def mockMvc = MockMvcBuilders.standaloneSetup(new StudentController(serviceMock)).build()

    def "should return CREATED status with Location header set on valid register user request"() {
        given:
            def request = StudentData.builder()
                    .username('jon_snow')
                    .password('nightwatch123')
                    .firstName('Jon')
                    .lastName('Snow')
                    .build()
        and:
            serviceMock.createStudent(_ as StudentData) >> Mock(Student) { getId() >> 1L }

        expect:
            mockMvc.perform(post('/student').content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(status().isCreated())
                    .andExpect(header().string('Location', '/student/1'))
    }

    @Unroll
    def "should return BAD REQUEST status on request with missing data"() {
        expect:
            mockMvc.perform(post('/student').content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(status().isBadRequest())

        where:
            request << [
                    new StudentData('', 'nightwatch123', 'Jon', 'Snow'),
                    new StudentData('jon_snow', '', 'Jon', 'Snow'),
                    new StudentData('jon_snow', 'nightwatch123', '', 'Snow'),
                    new StudentData('jon_snow', 'nightwatch123', 'Jon', ''),
                    new StudentData(null, 'nightwatch123', 'Jon', 'Snow'),
                    new StudentData('jon_snow', null, 'Jon', 'Snow'),
                    new StudentData('jon_snow', 'nightwatch123', null, 'Snow'),
                    new StudentData('jon_snow', 'nightwatch123', 'Jon', null)
            ]
    }
}
