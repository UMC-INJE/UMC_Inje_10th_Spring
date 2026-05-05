
package com.example.umc_10th_chiki.domain.point.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.umc_10th_chiki.domain.point.entity.Point;



@Repository

public interface PointRepository extends JpaRepository<Point, Long> {

}

