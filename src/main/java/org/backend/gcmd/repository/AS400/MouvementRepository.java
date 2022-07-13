package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.as400.MouvementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MouvementRepository extends JpaRepository<MouvementEntity, Long> {


    @Query(value = "SELECT mv FROM MouvementEntity mv WHERE " +
            " mv.numeroEscale = :numeroEscale And mv.codeMouvementNavire in( '3' , '5' , '1'  , '2' )  ")
    List<MouvementEntity> findByNumeroEscale(Double numeroEscale);

    @Query(value = "SELECT mv FROM MouvementEntity mv WHERE " +
            " mv.numeroEscale = :numeroEscale And mv.codeMouvementNavire in( '3' , '5' , '1'  , '2' )  ")
    Page<MouvementEntity> e(Double numeroEscale  , Pageable pageable);

}