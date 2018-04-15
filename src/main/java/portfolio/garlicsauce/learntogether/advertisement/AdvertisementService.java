package portfolio.garlicsauce.learntogether.advertisement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapper advertisementMapper;

    Advertisement createAdvertisement(AdvertisementData advertisementData) {
        return advertisementRepository.save(advertisementMapper.map(advertisementData));
    }

    Advertisement findAd(long id) {
        return advertisementRepository.findOne(id);
    }
}
