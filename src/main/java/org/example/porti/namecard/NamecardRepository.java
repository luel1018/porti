package org.example.porti.namecard;

import org.example.porti.namecard.model.Namecard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NamecardRepository extends JpaRepository<Namecard, Long> {
}
