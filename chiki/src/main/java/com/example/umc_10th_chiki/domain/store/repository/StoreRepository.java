
package com.example.umc_10th_chiki.domain.store.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.umc_10th_chiki.domain.store.entity.Store;



@Repository

public interface StoreRepository extends JpaRepository<Store, Long> {

}

