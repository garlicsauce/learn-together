package portfolio.garlicsauce.learntogether.advertisement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/advertisement")
@RequiredArgsConstructor
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @PostMapping
    public ResponseEntity createLearningAd(@RequestBody @Valid AdvertisementData request) {
        Long adId = advertisementService.createAdvertisement(request).getId();

        UriComponents newResourceUri = UriComponentsBuilder.fromPath(String.format("/advertisement/%s", adId))
                .build();

        return ResponseEntity.created(newResourceUri.toUri())
                .build();
    }

    @GetMapping("/{id}")
    public Advertisement getAd(@PathVariable("id") long id) {
        return advertisementService.findAd(id);
    }
}
