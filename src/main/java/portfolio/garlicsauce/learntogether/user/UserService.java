package portfolio.garlicsauce.learntogether.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;
    private final UserDataFactory userDataFactory;

    public UserData findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        return userDataFactory.create(user);
    }
}
