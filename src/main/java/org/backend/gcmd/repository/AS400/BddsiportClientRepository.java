package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.as400.BddsiporClientEntity;
import org.backend.gcmd.entity.prestati.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BddsiportClientRepository extends JpaRepository<BddsiporClientEntity, Long> {

    BddsiporClientEntity findByCodeConsignataire(String codeConsignataire);
}
