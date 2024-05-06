package lesson_3.resources;

import lesson_3.dto.ProductDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

//@Service
@RequiredArgsConstructor
public class WithWebClient {
    private final WebClient webClient;

    @Value("${demoAPI.post}")
    private String postURL;

    @Value("${demoAPI.put}")
    private String putURL;

    @Value("${demoAPI.delete}")
    private String deleteURL;

    @Value("${demoAPI.get}")
    private String getURL;

    @Value("${demoAPI.getList}")
    private String getListURL;

    public List<ProductDTO> getAll(Pageable pageable) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(getListURL)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize());

        return webClient.get()
                .uri(builder.toUriString())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductDTO>>() {
                })
                .block();
    }

    public ProductDTO getById(@NonNull Integer id) {
        return webClient.get()
                .uri(getURL, id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ProductDTO>() {
                })
                .block();
    }

    public void save(@NonNull ProductDTO productDTO) {
        webClient.post()
                .uri(postURL)
                .body(BodyInserters.fromValue(productDTO))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void update(@NonNull ProductDTO productDTO) {
        webClient.put()
                .uri(putURL)
                .body(BodyInserters.fromValue(productDTO))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void delete(@NonNull Integer id) {
        webClient.delete()
                .uri(deleteURL, id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
