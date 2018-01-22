package portfolio.garlicsauce.learntogether.student

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class StudentControllerSpec extends Specification {

    def objectMapper = new ObjectMapper()

    def serviceMock = Mock(StudentService)
    def mockMvc = MockMvcBuilders.standaloneSetup(new StudentController(serviceMock)).build()

    def "should POST /user and return CREATED status with Location header set"() {
        given:
            def requestMock = Mock(StudentData)
        and:
            serviceMock.createStudent(_ as StudentData) >> Mock(Student) { getId() >> 1L }

        expect:
            mockMvc.perform(post('/student').content(objectMapper.writeValueAsString(requestMock)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(status().isCreated())
                    .andExpect(header().string('Location', '/student/1'))
    }
}
