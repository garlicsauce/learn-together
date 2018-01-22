package portfolio.garlicsauce.learntogether.student

import spock.lang.Specification

class StudentMapperSpec extends Specification {

    def mapper = new StudentMapper()

    def "should map dto to entity"() {
        given:
            def dto = new StudentData('login', 'password', 'firstName', 'lastName')

        when:
            def entity = mapper.map(dto)

        then:
            with(entity) {
                login == 'login'
                password == 'password'
                firstName == 'firstName'
                lastName == 'lastName'
            }
    }
}
