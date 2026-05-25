package com.example.umc10th.domain.term.Repository;

import com.example.umc10th.domain.term.Entity.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {
}
