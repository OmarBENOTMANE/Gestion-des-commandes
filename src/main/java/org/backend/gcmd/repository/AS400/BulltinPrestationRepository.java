package org.backend.gcmd.repository.AS400;


import org.backend.gcmd.entity.as400.BulltinPrestationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BulltinPrestationRepository extends JpaRepository<BulltinPrestationEntity, Long> {

    Optional<BulltinPrestationEntity> findByNumeroBulletinPrestation(String numBp);


}