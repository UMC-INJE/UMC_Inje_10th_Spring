package com.example.umc10th.domain.store.Repository;

import com.example.umc10th.domain.store.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("""
            select
                case
                    when count(s) > 0 then true
                    else false
                end
            from Store s
            where s.id = :storeId and s.owner.id = :ownerId
            """)
    boolean existsOwnerStore(@Param("ownerId") Long ownerId, @Param("storeId") Long storeId);
}
