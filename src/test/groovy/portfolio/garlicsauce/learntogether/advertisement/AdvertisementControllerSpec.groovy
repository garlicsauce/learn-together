package portfolio.garlicsauce.learntogether.advertisement

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AdvertisementControllerSpec extends Specification {

    def objectMapper = new ObjectMapper()

    def serviceMock = Mock(AdvertisementService)
    def mockMvc = MockMvcBuilders.standaloneSetup(new AdvertisementController(serviceMock)).build()

    def "should return CREATED status with Location header set on valid create advertisement request"() {
        given:
            def request = AdvertisementData.builder()
                    .university('Warsaw University of Technology')
                    .subject('Mathematics 1')
                    .authorUsername('jon_snow')
                    .build()
        and:
            serviceMock.createAdvertisement(request) >> Mock(Advertisement) { getId() >> 1L }

        expect:
            mockMvc.perform(post('/advertisement').content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(status().isCreated())
                    .andExpect(header().string('Location', '/advertisement/1'))
    }

    @Unroll
    def "should return BAD REQUEST status on request with missing data"() {
        expect:
            mockMvc.perform(post('/advertisement').content(objectMapper.writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(status().isBadRequest())

        where:
            request << [
                new AdvertisementData('', 'Mathematics 1', 'jon_snow'),
                new AdvertisementData('Warsaw University of Technology', '', 'jon_snow'),
                new AdvertisementData('Warsaw University of Technology', 'Mathematics 1', ''),
                new AdvertisementData(null, 'Mathematics 1', 'jon_snow'),
                new AdvertisementData('Warsaw University of Technology', null, 'jon_snow'),
                new AdvertisementData('Warsaw University of Technology', 'Mathematics 1', null)
            ]
    }
}
