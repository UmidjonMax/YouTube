package dasturlash.uz.service;

import dasturlash.uz.entity.ProfileRoleEntity;
import dasturlash.uz.enums.ProfileRoleEnum;
import dasturlash.uz.repository.ProfileRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProfileRoleService {
    @Autowired
    private ProfileRoleRepository profileRoleRepository;

    public void create(String profileId, List<ProfileRoleEnum> roleList) {
        for (ProfileRoleEnum roleEnum : roleList) {
            ProfileRoleEntity entity = new ProfileRoleEntity();
            entity.setProfileId(profileId);
            entity.setRoles(roleEnum);
            profileRoleRepository.save(entity);
        }
    }

    public void merge(String profileId, List<ProfileRoleEnum> newList) {
        List<ProfileRoleEnum> oldList = profileRoleRepository.getRoleListByProfileId(profileId);
        newList.stream().filter(n -> !oldList.contains(n)).forEach(pe -> create(profileId, pe)); // create
        oldList.stream().filter(old -> !newList.contains(old)).forEach(pe-> profileRoleRepository.deleteByIdAndRoleEnum(profileId, pe));
    }


    public void create(String profileId, ProfileRoleEnum role) {
        ProfileRoleEntity entity = new ProfileRoleEntity();
        entity.setProfileId(profileId);
        entity.setRoles(role);
        profileRoleRepository.save(entity);
    }
    public void deleteRolesByProfileId(String profileId) {
        profileRoleRepository.deleteByProfileId(profileId);
    }

    public List<ProfileRoleEnum> getByProfileId(String profileId) {
        return profileRoleRepository.getRoleListByProfileId(profileId);
    }
}
