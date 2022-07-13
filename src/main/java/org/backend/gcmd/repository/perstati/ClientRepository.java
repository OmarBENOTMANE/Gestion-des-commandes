package org.backend.gcmd.repository.perstati;

import org.backend.gcmd.entity.prestati.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query("SELECT c FROM ClientEntity c  where c.codeClient  =   :codeClient")
    Optional<ClientEntity> findByCodeClientt(@Param("codeClient") String codeClient);


}
