package lesson_3.resources;

import lesson_3.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "FeignClient", url = "${demoAPI.base}")
public interface WithOpenFeign {

    @GetMapping("/list")
    List<ProductDTO> getAll(Pageable pageable);

    @GetMapping("/get/{id}")
    ProductDTO getById(@PathVariable Integer id);

    @PostMapping("/create")
    void save(@RequestBody ProductDTO productDTO);

    @PutMapping("/update")
    void update(@RequestBody ProductDTO productDTO);

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Integer id);
}
