package lesson_3.controller;

import lesson_3.dto.ProductDTO;
import lesson_3.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ConsumerController {
    private final ConsumerService consumerService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDTO>> getAll(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "5", required = false) int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(consumerService.getAll(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(consumerService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody ProductDTO productDTO) {
        consumerService.save(productDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody ProductDTO productDTO) {
        consumerService.update(productDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        consumerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
