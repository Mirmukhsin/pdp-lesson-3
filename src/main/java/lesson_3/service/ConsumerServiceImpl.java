package lesson_3.service;

import lesson_3.dto.ProductDTO;
import lesson_3.resources.WithOpenFeign;
import lesson_3.resources.WithRestTemplate;
import lesson_3.resources.WithWebClient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
    //        private final WithRestTemplate resourceService;
//    private final WithWebClient resourceService;
    private final WithOpenFeign resourceService;

    @Override
    public List<ProductDTO> getAll(Pageable pageable) {
        return resourceService.getAll(pageable);
    }

    @Override
    public ProductDTO getById(@NonNull Integer id) {
        return resourceService.getById(id);
    }

    @Override
    public void save(@NonNull ProductDTO productDTO) {
        resourceService.save(productDTO);
    }

    @Override
    public void update(@NonNull ProductDTO productDTO) {
        resourceService.update(productDTO);
    }

    @Override
    public void delete(@NonNull Integer id) {
        resourceService.delete(id);
    }
}
