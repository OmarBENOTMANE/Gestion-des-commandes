package org.backend.gcmd.repository.AS400;

import org.backend.gcmd.entity.as400.CommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("commandeRepositoryAS400")
public interface CommandeRepository extends JpaRepository<CommandeEntity, Long> {


}