package portfolio.garlicsauce.learntogether.advertisement;

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
public class AdvertisementData {

    @JsonProperty(required = true)
    @NotBlank
    private String university;

    @JsonProperty(required = true)
    @NotBlank
    private String subject;

    @JsonProperty(required = true)
    @NotBlank
    private String authorUsername;
}
