package org.example.porti.namecard;

import org.example.porti.namecard.model.Namecard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NamecardRepository extends JpaRepository<Namecard, Long> {
    Optional<Namecard> findByUserIdx(Long userIdx);
}
