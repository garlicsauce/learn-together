package portfolio.garlicsauce.learntogether.advertisement

import portfolio.garlicsauce.learntogether.student.Student
import portfolio.garlicsauce.learntogether.student.StudentService
import spock.lang.Specification
import spock.lang.Subject

class AdvertisementMapperSpec extends Specification {

    def studentServiceMock = Mock(StudentService)

    @Subject
    def mapper = new AdvertisementMapper(studentServiceMock)

    def "should throw exception if user with provided username doesn't exist"() {
        given:
            def data = new AdvertisementData('Warsaw University of Technology', 'Mathematics 1', 'jon_snow')
        and:
            studentServiceMock.findStudent('jon_snow') >> Optional.empty()

        when:
            mapper.map(data)

        then:
            thrown(IllegalArgumentException)
    }

    def "should map request to entity"() {
        given:
            def data = new AdvertisementData('Warsaw University of Technology', 'Mathematics 1', 'jon_snow')
        and:
            def studentMock = Mock(Student)
            studentServiceMock.findStudent('jon_snow') >> Optional.of(studentMock)

        expect:
            with(mapper.map(data)) {
                university == 'Warsaw University of Technology'
                subject == 'Mathematics 1'
                author == studentMock
            }
    }
}
