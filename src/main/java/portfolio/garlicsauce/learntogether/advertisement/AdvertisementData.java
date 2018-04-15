package portfolio.garlicsauce.learntogether.advertisement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementData {

    @JsonProperty(required = true)
    private String university;

    @JsonProperty(required = true)
    private String subject;

    @JsonProperty(required = true)
    private String authorUsername;
}
