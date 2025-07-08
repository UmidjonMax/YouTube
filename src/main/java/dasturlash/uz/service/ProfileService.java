package dasturlash.uz.service;

import dasturlash.uz.dto.profile.CreateProfileDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import youtube_step_by_step.exceptions.AppBadException;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public CreateProfileDTO create(CreateProfileDTO dto) {
        Optional<ProfileEntity> profile = profileRepository.findByEmail(dto.getEmail());
        if (profile.isPresent()) {
            throw new AppBadException("Email already in use");
        }
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setEmail(dto.getEmail());
        profileEntity.setName(dto.getName());
        profileEntity.setSurname(dto.getSurname());
        profileEntity.setRole(dto.getRole());
        profileRepository.save(profileEntity);
        CreateProfileDTO response = toDTO(profileEntity);
        return response;
    }

    public CreateProfileDTO toDTO(ProfileEntity entity) {
        CreateProfileDTO dto = new CreateProfileDTO();
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setRole(entity.getRole());
        return dto;
    }
}
