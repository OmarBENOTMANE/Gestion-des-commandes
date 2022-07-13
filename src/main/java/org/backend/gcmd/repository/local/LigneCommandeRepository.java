package org.backend.gcmd.repository.local;

import org.backend.gcmd.entity.local.LigneCommandeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommandeEntity, Long> {


    Page<LigneCommandeEntity> findAllByNumeroBonCommande(String numDos , Pageable pageable );

}