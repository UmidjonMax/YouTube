package dasturlash.uz.repository;

import dasturlash.uz.entity.ProfileRoleEntity;
import dasturlash.uz.enums.ProfileRoleEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProfileRoleRepository extends CrudRepository<ProfileRoleEntity, Integer> {
    @Query("select roles from ProfileRoleEntity where profileId =?1")
    List<ProfileRoleEnum> getRoleListByProfileId(String profileId);

    @Transactional
    @Modifying
    @Query("DELETE from ProfileRoleEntity where profileId =?1 and roles =?2")
    void deleteByIdAndRoleEnum(String profileId, ProfileRoleEnum role);

    @Transactional
    @Modifying
    @Query("Delete from ProfileRoleEntity where profileId =?1")
    void deleteByProfileId(String profileId);
}
