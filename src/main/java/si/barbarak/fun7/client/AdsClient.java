package si.barbarak.fun7.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import si.barbarak.fun7.exception.ExternalServiceError;

public class AdsClient {
    private final WebClient client;

    public AdsClient(String url, String username, String password) {
        client = WebClient.builder()
                .baseUrl(url)
                .filter(ExchangeFilterFunctions.basicAuthentication(username, password))
                .build();
    }

    public boolean areAdsEnabledForCountry(String cc) throws ExternalServiceError {
        AdResponse responseBody = client.get()
                .uri("fun7-ad-partner", uri -> uri.queryParam("countryCode", cc).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (err -> Mono.error(new ExternalServiceError())))
                .bodyToMono(AdResponse.class)
                .block();

        if (responseBody == null) {
            throw new ExternalServiceError();
        }

        return "sure, why not!".equalsIgnoreCase(responseBody.getAds());
    }
}

@Data
@NoArgsConstructor
class AdResponse {
    private String ads;
}
