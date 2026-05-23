package com.example.umc10th.domain.food.Repository;

import com.example.umc10th.domain.Food.Enums.FoodName;
import com.example.umc10th.domain.food.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByNameIn(Collection<FoodName> names);
}
