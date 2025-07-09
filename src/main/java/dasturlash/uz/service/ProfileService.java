package dasturlash.uz.service;

import dasturlash.uz.dto.profile.CreateProfileDTO;
import dasturlash.uz.dto.profile.GetProfileDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.enums.ProfileStatusEnum;
import dasturlash.uz.exceptions.AppBadException;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileRoleService profileRoleService;

    public CreateProfileDTO create(CreateProfileDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            throw new AppBadException("User already exists");
        }
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setEmail(dto.getEmail());
        profileEntity.setName(dto.getName());
        profileEntity.setSurname(dto.getSurname());
        profileEntity.setStatus(ProfileStatusEnum.ACTIVE);
        profileRepository.save(profileEntity);
        profileRoleService.merge(profileEntity.getId(), dto.getRoles());
        CreateProfileDTO response = toDTO(profileEntity);
        response.setRoles(dto.getRoles());
        return response;
    }

    public GetProfileDTO getDetails(){
        return null;
    }

    public CreateProfileDTO toDTO(ProfileEntity entity) {
        CreateProfileDTO dto = new CreateProfileDTO();
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        return dto;
    }
}
