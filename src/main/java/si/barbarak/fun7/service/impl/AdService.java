package si.barbarak.fun7.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import si.barbarak.fun7.client.AdsClient;
import si.barbarak.fun7.config.AdExternalClientConfig;
import si.barbarak.fun7.exception.ExternalServiceError;
import si.barbarak.fun7.service.IAdService;

@Service
public class AdService implements IAdService {

    final
    AdExternalClientConfig clientConfig;

    public AdService(AdExternalClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    @Cacheable("ads_enabled")
    public boolean isEnabled(String cc) throws ExternalServiceError {
        return new AdsClient(clientConfig.getUrl(),
                clientConfig.getUsername(),
                clientConfig.getPassword())
                .areAdsEnabledForCountry(cc);
    }
}
