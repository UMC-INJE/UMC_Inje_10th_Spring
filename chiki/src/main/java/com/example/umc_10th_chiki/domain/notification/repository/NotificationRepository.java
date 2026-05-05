
package com.example.umc_10th_chiki.domain.notification.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.umc_10th_chiki.domain.notification.entity.Notification;



@Repository

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}

