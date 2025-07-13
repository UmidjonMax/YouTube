package dasturlash.uz.service;

import dasturlash.uz.dto.AttachDTO;
import dasturlash.uz.entity.AttachEntity;
import dasturlash.uz.repository.AttachRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.ArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AttachService {

    @Autowired
    private AttachRepository attachRepository;

    private final String uploadFolder = "upload/";

    public String saveToSystem(MultipartFile file) {
        // 1. Fayllar saqlanadigan papkani yaratish
        File folder = new File(uploadFolder);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (!created) {
                throw new RuntimeException("Papka yaratib bo'lmadi: " + uploadFolder);
            }
        }

        // 2. Fayl nomi va kengaytmasini olish
        String id = UUID.randomUUID().toString();
        String extension = getExtension(file.getOriginalFilename());
        String fileName = id + "." + extension;

        // 3. Faylni diskka yozish
        try {
            Path path = Paths.get(uploadFolder + fileName);
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            System.out.println("📛 Fayl yozishda xatolik: " + e.getMessage());
            throw new RuntimeException("Faylni saqlab bo'lmadi", e);
        }

        // 4. Fayl ma'lumotlarini DBga yozish
        AttachEntity entity = new AttachEntity();
        entity.setId(id);
        entity.setOriginalName(file.getOriginalFilename());
        entity.setExtension(extension);
        entity.setSize(file.getSize());
        entity.setPath(fileName);
        entity.setContentType(file.getContentType());
        entity.setCreatedDate(LocalDateTime.now());

        attachRepository.save(entity);
        return id;
    }


    public void loadOpen(String id, HttpServletResponse response) {
        // 1. Faylni bazadan olish
        AttachEntity entity = get(id);
        response.setContentType(entity.getContentType()); // MIME turi (jpg, png, pdf...)

        // 2. Faylni oqib chiqish va foydalanuvchiga jo‘natish
        try (InputStream in = new FileInputStream(uploadFolder + entity.getPath())) {
            IOUtils.copy(in, response.getOutputStream());
            response.flushBuffer(); // Outputni tozalab yuborish
        } catch (IOException e) {
            System.out.println("📛 Faylni ochishda xatolik: " + e.getMessage());
            throw new RuntimeException("Faylni ochib bo'lmadi", e);
        }
    }

    public Page<AttachDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<AttachEntity> entityPage = attachRepository.findAll(pageable);

        List<AttachDTO> dtoList = new ArrayList<>();
        for (AttachEntity entity : entityPage.getContent()) {
            dtoList.add(toDTO(entity));
        }

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public boolean delete(String id) {
        AttachEntity entity = get(id);
        File file = new File(uploadFolder + entity.getPath());
        if (file.exists()) {
            file.delete();
        }
        attachRepository.deleteById(id);
        return true;
    }

    private AttachEntity get(String id) {
        return attachRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Attach not found: " + id));
    }

    private String getExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        return (i >= 0) ? fileName.substring(i + 1) : "";
    }

    private AttachDTO toDTO(AttachEntity entity) {
        AttachDTO dto = new AttachDTO();
        dto.setId(entity.getId());
        dto.setOriginalName(entity.getOriginalName());
        dto.setSize(entity.getSize());
        dto.setUrl("/api/v1/attach/open/" + entity.getId());
        return dto;
    }
}
