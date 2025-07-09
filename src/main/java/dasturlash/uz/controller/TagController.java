package dasturlash.uz.controller;

import dasturlash.uz.dto.TagRequestDTO;
import dasturlash.uz.dto.TagResponseDTO;
import dasturlash.uz.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    // 👉 Create: oddiy foydalanuvchi ham qilsa bo‘ladi (yoki Moderator)
    @PostMapping("")
    public ResponseEntity<TagResponseDTO> create(@RequestBody TagRequestDTO dto) {
        return ResponseEntity.ok(tagService.create(dto));
    }

    // 🔐 Update: faqat ADMIN uchun
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TagResponseDTO> update(@PathVariable Integer id,
                                                 @RequestBody TagRequestDTO dto) {
        return ResponseEntity.ok(tagService.update(id, dto));
    }

    // 🔐 Delete: faqat ADMIN uchun
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 📄 Barchaga ko‘rinadigan list
    @GetMapping("")
    public ResponseEntity<List<TagResponseDTO>> getAll() {
        return ResponseEntity.ok(tagService.getAll());
    }
}
