package dasturlash.uz.repository;


import dasturlash.uz.entity.EmailHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity, String> {

    Optional<EmailHistoryEntity> findTopByToAccountOrderByCreatedDateDesc(String account);

    @Query("from EmailHistoryEntity where toAccount = ?1 order by createdDate desc limit 1")
    Optional<EmailHistoryEntity> findLastByAccount(String account);

}
