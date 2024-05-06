package lesson_3.resources;

import lesson_3.dto.ProductDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

//@Service
@RequiredArgsConstructor
public class WithRestTemplate {
    private final RestTemplate restTemplate;

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

        return restTemplate.getForObject(builder.toUriString(), List.class);
    }

    public ProductDTO getById(@NonNull Integer id) {
        return restTemplate.getForObject(getURL, ProductDTO.class, id);
    }

    public void save(@NonNull ProductDTO productDTO) {
        restTemplate.postForObject(postURL, productDTO, Void.class);
    }

    public void update(@NonNull ProductDTO productDTO) {
        restTemplate.put(putURL, productDTO, Void.class);
    }

    public void delete(@NonNull Integer id) {
        restTemplate.delete(deleteURL, id);
    }
}
