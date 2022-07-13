package org.backend.gcmd.repository.local;

import org.backend.gcmd.entity.local.LigneCommandeEntity;
import org.backend.gcmd.entity.local.LignePrestationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("LignePrestationRepositoryGCMD")
public interface LignePrestationRepository extends JpaRepository<LignePrestationEntity  , Long> {

    Page<LignePrestationEntity> findAllByNumeroDossier(String numDos , Pageable pageable );

    Page<LignePrestationEntity> findAllByNumeroBonCommande(String numDos , Pageable pageable );

    LignePrestationEntity findByNumeroBonCommandeAndCodePrestation(String numCmd  , Double codePrestation);
}
