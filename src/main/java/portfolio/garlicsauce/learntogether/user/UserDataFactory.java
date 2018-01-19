package portfolio.garlicsauce.learntogether.user;

import org.springframework.stereotype.Component;
import portfolio.garlicsauce.learntogether.factory.Factory;

@Component
public class UserDataFactory implements Factory<UserData, User> {

    @Override
    public UserData create(User user) {
        return UserData.builder()
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
