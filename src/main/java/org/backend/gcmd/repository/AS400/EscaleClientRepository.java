package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.as400.EscaleClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EscaleClientRepository extends JpaRepository<EscaleClientEntity, Long> {

    @Query(value = "SELECT * FROM DESTCG e WHERE e.NUESCG = :numEscal LIMIT 1" , nativeQuery = true)
    Optional<EscaleClientEntity>  findByNumEscal(Double numEscal);
}
