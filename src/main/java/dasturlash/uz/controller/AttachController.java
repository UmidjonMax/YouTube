package dasturlash.uz.controller;

import dasturlash.uz.dto.AttachDTO;
import dasturlash.uz.service.AttachService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/attach")
public class AttachController {

    @Autowired
    private AttachService attachService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String id = attachService.saveToSystem(file);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/open/{id}")
    public void open(@PathVariable String id, HttpServletResponse response) {
        attachService.loadOpen(id, response);
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable String id, HttpServletResponse response) {
        attachService.loadOpen(id, response);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<AttachDTO>> pagination(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(attachService.pagination(page, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return ResponseEntity.ok(attachService.delete(id));
    }
}