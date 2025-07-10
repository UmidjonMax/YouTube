package dasturlash.uz.service;

import dasturlash.uz.dto.profile.CreateProfileDTO;
import dasturlash.uz.dto.profile.GetProfileDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.enums.ProfileStatusEnum;
import dasturlash.uz.exceptions.AppBadException;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.util.SecurityUtil;
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
        Optional<ProfileEntity> entity = profileRepository.findById(SecurityUtil.currentProfileId());
        if (entity.isEmpty()) {
            throw new AppBadException("Profile not found");
        }
        ProfileEntity profileEntity = entity.get();
        return toGetDTO(profileEntity);
    }



    public GetProfileDTO toGetDTO(ProfileEntity entity){
        GetProfileDTO dto = new GetProfileDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPhotoUrl(entity.getPhotoUrl());
        return dto;
    }
    public CreateProfileDTO toDTO(ProfileEntity entity) {
        CreateProfileDTO dto = new CreateProfileDTO();
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        return dto;
    }
}
