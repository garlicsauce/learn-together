package portfolio.garlicsauce.learntogether.student

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import spock.lang.Specification

class StudentMapperSpec extends Specification {

    def passwordEncoderMock = Mock(BCryptPasswordEncoder)
    def mapper = new StudentMapper(passwordEncoderMock)

    def "should map dto to entity"() {
        given:
            def dto = new StudentData('username', 'password', 'firstName', 'lastName')
        and:
            passwordEncoderMock.encode(dto.password) >> 'hash'

        when:
            def entity = mapper.map(dto)

        then:
            with(entity) {
                username == 'username'
                password == 'hash'
                firstName == 'firstName'
                lastName == 'lastName'
            }
    }
}
