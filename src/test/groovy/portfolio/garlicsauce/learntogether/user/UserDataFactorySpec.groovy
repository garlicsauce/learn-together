package portfolio.garlicsauce.learntogether.user

import spock.lang.Specification

class UserDataFactorySpec extends Specification {

    private UserDataFactory userDataFactory = new UserDataFactory()

    def "should create user data based on user entity"() {
        given:
            User user = new User(login: 'login', firstName: 'Jan', lastName: 'Kowalski')
        when:
            UserData result = userDataFactory.create(user)
        then:
            result.login == 'login'
            result.firstName == 'Jan'
            result.lastName == 'Kowalski'
    }
}