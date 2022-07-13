package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.as400.EscaleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EscaleRepository extends JpaRepository<EscaleEntity, Long>, JpaSpecificationExecutor<EscaleEntity> {

    @Query(value = "SELECT e  FROM EscaleEntity e "
            + "	LEFT JOIN BulltinPrestationEntity b "
            + "	ON e.numeroEscale = b.numeroEscale "
            + "LEFT JOIN MouvementEntity m "
            + "ON e.numeroEscale= m.numeroEscale"
            + " WHERE b.numeroEscale IS NULL "
            + " AND m.codeMouvementNavire='5' " +
            "order by  e.numeroEscale desc  ")
    Page<EscaleEntity> findNavirTOBill(Pageable page);

    @Query(value = "SELECT e FROM EscaleEntity e "
            + "	LEFT JOIN BulltinPrestationEntity b "
            + "	ON e.numeroEscale = b.numeroEscale "
            + " LEFT JOIN MouvementEntity m "
            + " ON e.numeroEscale= m.numeroEscale"
            + " WHERE b.numeroEscale IS NULL "
            + " AND m.codeMouvementNavire='5' "
            + " AND e.typeNavire in('2' , '20' , '21'  , '26')"
            + " AND (:numeroEscale is null or e.numeroEscale = :numeroEscale)"
            + " AND (:nomNavire is null or e.nomNavire LIKE %:nomNavire% )"
            + " AND (:numeroLloyd is null or e.numeroLloyd LIKE %:numeroLloyd% ) "
            + " order by  e.numeroEscale desc ")
    Page<EscaleEntity> findNavirTOBillFiltered(Double numeroEscale, String nomNavire, String numeroLloyd, Pageable page);

    @Query(value = "SELECT  e from EscaleEntity e  order by  e.numeroEscale desc ")
    Page<EscaleEntity> findAll(Pageable page);

    EscaleEntity findByNumeroEscale(Double numeroEscal);

}