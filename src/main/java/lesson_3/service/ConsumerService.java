package lesson_3.service;

import lesson_3.dto.ProductDTO;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConsumerService {
    List<ProductDTO> getAll(Pageable pageable);

    ProductDTO getById(@NonNull Integer id);

    void save(@NonNull ProductDTO productDTO);

    void update(@NonNull ProductDTO productDTO);

    void delete(@NonNull Integer id);
}
