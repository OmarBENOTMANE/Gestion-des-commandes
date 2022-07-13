package org.backend.gcmd.repository.local;

import org.backend.gcmd.entity.local.MouvementPrestationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MouvementPrestationRepository extends JpaRepository<MouvementPrestationEntity, Long> {

    MouvementPrestationEntity findByCodePrestation(String codePrestation);

    Optional<MouvementPrestationEntity> findByCodeMouvement(String codeMouvement);
}
