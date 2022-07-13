package org.backend.gcmd.repository.perstati;

import org.backend.gcmd.entity.prestati.LibellePrestationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibellePrestationRepository extends JpaRepository<LibellePrestationEntity, Long> {

    Optional<LibellePrestationEntity> findByCodePrestation(Double numPres);

    @Query(value = "SELECT  lb FROM  LibellePrestationEntity lb WHERE " +
                   "  lb.codePrestation  = 20101 OR " +
                    "  lb.codePrestation  = 20102")
    Page<LibellePrestationEntity> findPresNav(Pageable page);


}
