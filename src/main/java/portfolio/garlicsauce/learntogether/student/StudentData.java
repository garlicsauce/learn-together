package portfolio.garlicsauce.learntogether.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentData {

    @JsonProperty(required = true)
    @NotBlank
    private String username;

    @JsonProperty(required = true)
    @NotBlank
    private String password;

    @JsonProperty(required = true)
    @NotBlank
    private String firstName;

    @JsonProperty(required = true)
    @NotBlank
    private String lastName;
}
