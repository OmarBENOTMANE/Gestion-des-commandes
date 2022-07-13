package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.as400.LignePrestationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LignePrestationRepository extends JpaRepository<LignePrestationEntity, Long> {

}