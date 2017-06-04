package portfolio.garlicsauce.learntogether.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {

    private String login;
    private String firstName;
    private String lastName;
}
